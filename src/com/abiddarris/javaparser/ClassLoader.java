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
    private EditablePackage[] packages;

    public ClassLoader(File root) {
        this.root = root;
    }
    
    public Class loadClass(String name) {
        try {
            return loadEditableClass(name);
        } catch (Exception e) {           
        }
        
        try {
            return loadClassWrapper(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }   

    public ClassWrapper loadClassWrapper(String name) throws ClassNotFoundException {
        return new ClassWrapper(this,java.lang.Class.forName(name));
    }  
    
    public EditableClass loadEditableClass(String name) {
        String innerClassPath = null;
        int dollarSign = name.indexOf("$");
        if(dollarSign != -1) {
            innerClassPath = name.substring(dollarSign + 1);
            name = name.substring(0,dollarSign);
        }
        JavaFile src = new JavaFile(this,new File(root,name.replace(".","/") + ".java").getPath());
        EditableClass[] classes = src.getClasses();
        
        EditableClass clazz = null;
        for(EditableClass _clazz : classes) {
            if(Modifier.isPublic(_clazz.getModifiers())) {
                clazz = _clazz;
                break;
            }
        }
        
        if(clazz == null) clazz = classes[0];
        
        if(innerClassPath != null) {
            return (EditableClass) clazz.getInnerClass(innerClassPath);
        }
  
        return clazz;    
    }
    
    
    public EditablePackage getPackage(String name) {
        for(EditablePackage editablePackage : getPackages()) {
            if(editablePackage.getName().equals(name)) {
                return editablePackage;
            }
        }
        return null;
    }
    
    public EditablePackage[] getPackages() {
        if(packages == null) {
            List<EditablePackage> editablePackages = new ArrayList<>();
            createPackages(editablePackages,root);   
            packages = editablePackages.toArray(new EditablePackage[0]);
        }
        return packages;
    }

    private void createPackages(List<EditablePackage> editablePackages, File packageFile) {
        String name = packageFile.getPath()
            .replace(root.getPath(),"")
            .replace("/",".");
        if(name.startsWith(".")) name = name.substring(1);
        
        List<String> classPaths = new ArrayList<>();
        
        EditablePackage editablePackage = new EditablePackage(name);
        editablePackages.add(editablePackage);
        
        File[] files = packageFile.listFiles();         
        for(File file : files) {
            if(file.isDirectory()) {
                createPackages(editablePackages,file);
            } else {
                String classPath = file.getPath()
                               .replace(".java","")
                               .replace(root.getPath(),"")
                               .replace("/",".");
                               
                if(classPath.startsWith(".")) classPath = classPath.substring(1);
                classPaths.add(classPath);
            }
        }
        editablePackage.setClassPaths(this,classPaths.toArray(new String[0]));
    }
    
    
}
