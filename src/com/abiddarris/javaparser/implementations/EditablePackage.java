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

package com.abiddarris.javaparser.implementations;
 
import com.abiddarris.javaparser.Package;
import com.abiddarris.javaparser.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class EditablePackage extends Package {
    
    private EditableClass[] classes;
    private String name;
    private JavaFile[] javaFiles;

    public EditablePackage(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Annotation[] getDeclaredAnnotations() {
        //TODO :
        return null;
    }
    
    public JavaFile getJavaFile(String name) {
        for(JavaFile javaFile : javaFiles) {
            if(javaFile.getName().equals(name)) {
                return javaFile;
            }                 
        }
        return null;
    }

    public EditableClass[] getClasses() {
        if(classes == null) {
            List<EditableClass> classes = new ArrayList<>();           
            for(int i = 0; i < javaFiles.length; i++) {
                for(EditableClass clazz :  javaFiles[i].getClasses()) {
                    classes.add(clazz);
                }
            }
            this.classes = classes.toArray(new EditableClass[0]);
        }
        return classes;
    }
    
    public void setClassPaths(JavaFile[] javaFiles) {      
        classes = null;
        this.javaFiles = javaFiles;
    }
    
    public JavaFile[] getJavaFiles() {
        return javaFiles;
    }
}
