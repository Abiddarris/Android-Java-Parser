/*******************************************************************************
 * Copyright 2023 by Abiddarris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.abiddarris.javaparser;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.Collections;
import java.util.Comparator;

public class ClassLoader {
       
    private File root;

    public ClassLoader(File root) {
        this.root = root;
    }
    
    public Class loadClass(String name) {
        String innerClassPath = null;
        int dollarSign = name.indexOf("$");
        if(dollarSign != -1) {
            innerClassPath = name.substring(dollarSign + 1);
            name = name.substring(0,dollarSign);
        }
        File src = new File(root,name.replace(".","/") + ".java");
        if(!src.exists()) {           
            try {
                return loadClassWrapper(name);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } 
        
        
        EditableClass editableClass = loadEditableClass(name);      
        if(innerClassPath != null) {
            return editableClass.getInnerClass(innerClassPath);
        }
        return editableClass;
    }   

    public ClassWrapper loadClassWrapper(String name) throws ClassNotFoundException {
        return new ClassWrapper(this,java.lang.Class.forName(name));
    }  
    
    public EditableClass loadEditableClass(String name) {
        File src = new File(root,name.replace(".","/") + ".java");
        StringBuilder builder;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(src));
            builder = new StringBuilder();
            String data;
            while((data = reader.readLine()) != null) {
                builder.append(data)
                    .append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        Bracket parent = Bracket.parse(builder.toString(),"{","}");       
        List<Import> imports = handleImports(builder.substring(0,parent.children.get(0).start));

        EditableClass[] classes = new EditableClass[parent.children.size()];       
        for(int i = 0; i < classes.length; i++) {
            Bracket bracket = parent.children.get(i);
            EditableClass clazz = new EditableClass(null,imports,this,builder.toString(),bracket);
            classes[i] = clazz;
        }

        return classes[0];       
    }

    private List<Import> handleImports(String importStr) {
        List<Import> imports = new ArrayList<>();
        int importStart = 0;
        while ((importStart = importStr.indexOf("import ", importStart)) != - 1) {
            int semicolon = importStr.indexOf(";", importStart);
            importStart += "import ".length();

            Import _import = new Import(importStr.substring(importStart, semicolon));
            imports.add(_import);
        }
        return imports;
    }
   
}