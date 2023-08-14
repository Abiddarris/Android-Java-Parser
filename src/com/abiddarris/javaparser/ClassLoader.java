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

import com.abiddarris.javaparser.implementations.EditableClass;
import com.abiddarris.javaparser.implementations.EditablePackage;
import com.abiddarris.javaparser.wrappers.ClassWrapper;

public abstract class ClassLoader {
   
    private ClassLoader parent;
    
    private static final ClassLoader SYSTEM_CLASS_LOADER = new SystemClassLoader();
    
    ClassLoader(ClassLoader parent) {
        this.parent = parent;
    }
    
    ClassLoader() {       
    }
    
    public abstract Class loadClass(String name); 

    public abstract ClassWrapper loadClassWrapper(String name) throws ClassNotFoundException;       
    
    public abstract EditableClass loadEditableClass(String name);  
    
    public ClassLoader getParent() {
        return parent;
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
        return new EditablePackage[0];       
    }
    
    public static ClassLoader getSystemClassLoader() {
        return SYSTEM_CLASS_LOADER;
    }

}
