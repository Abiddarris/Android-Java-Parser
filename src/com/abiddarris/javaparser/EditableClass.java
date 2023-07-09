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

import java.util.List;

public class EditableClass implements Class {
    
    private boolean isAnnotation;
    private boolean isInterface;   
    private Class[] interfaces;
    private ClassInfo classInfo;
    private ClassLoader loader;
    private int modifiers;
    List<Import> imports;
    private String name;   
    private String simpleName;
    private String superClass;  
    private Type genericSuperclass;
    private Type[] genericInterfaces;
    private TypeVariable[] typeParameters;
     
    EditableClass(Class parent, List<Import> imports, ClassLoader loader, String code, Bracket bracket) {          
        this.loader = loader;
        this.imports = imports;   
        
        String before = code.substring(0,bracket.start);
        before = before.trim();

     
        classInfo = new ClassInfo(parent, code, bracket, this); 
        classInfo.load2(parent,bracket,code);
        
        isAnnotation = classInfo.isAnnotation;
        isInterface = classInfo.isInterface;
        name = classInfo.name;
        superClass = classInfo.superClass;
        typeParameters = classInfo.typeParameters;
        genericSuperclass = classInfo.genericSuperclassType;
        interfaces = classInfo.interfaces;
        genericInterfaces = classInfo.genericInterfaces;
        modifiers = classInfo.modifiers;
        simpleName = classInfo.simpleName;       
    }
    
    String resolvePathByName(String name) {      
        for(Import _import : getImports()) {
            if(_import.getName().equals(name)) {
                return _import.getPackage() + "." + _import.getName();
            }
        }
        return getPackage().getName() + "." + name;
    } 
    
    Class resolveClassByName(String name) {
        String path = resolvePathByName(name);   
        Class clazz; 
        try {
            clazz = getClassLoader().loadClass(path);     
        } catch (RuntimeException e) {
            path = "java.lang." + name;
            try {
                clazz = getClassLoader().loadClass(path);   
            } catch(RuntimeException e1) {
                clazz = getClassLoader().loadClass(name);
            }          
        }
        return clazz;
    }    

    @Override
    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public boolean isAnnotation() {
        return isAnnotation;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ClassLoader getClassLoader() {
        return loader;
    }
    
    @Override
    public synchronized TypeVariable<Class>[] getTypeParameters() {
        return typeParameters;
    }

    @Override
    public Class getSuperclass() {
        if(isInterface())
            return null;
            
        if(superClass == null) {
            return getClassLoader().loadClass("java.lang.Object");
        }
        
        String path = resolvePathByName(superClass);
        
        Class clazz = getClassLoader().loadClass(path);
        
        return clazz;
    }

    @Override
    public Type getGenericSuperclass() {
        if(genericSuperclass == null)
            return getSuperclass();
        return genericSuperclass;
    }

    @Override
    public Package getPackage() {       
        return classInfo.getPackage();
    }

    @Override
    public Class[] getInterfaces() {
        return interfaces;
    }

    @Override
    public Type[] getGenericInterfaces() {
        return genericInterfaces;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }
    
    @Override
    public String getTypeName() {
        return getName();
    }

    @Override
    public String getSimpleName() {
        return simpleName;
    }     

    @Override
    public Class[] getClasses() {
        return classInfo.getClasses();
    }

    public Import[] getImports() {
        return imports.toArray(new Import[0]);
    }
    
    public Class[] getActualClasses() {
        return classInfo.getActualClasses();
    }
    
    public Class getInnerClass(String name) {
        String innerClassPath = null;
        int dollarSign = name.indexOf("$");
        if(dollarSign != -1) {
            innerClassPath = name.substring(dollarSign + 1);
            name = name.substring(0,dollarSign);
        }
        
        for(Class clazz : getActualClasses()) {
            if(clazz.getSimpleName().equals(name)) {
                if(innerClassPath != null) {                  
                    return ((EditableClass)clazz).getInnerClass(innerClassPath);                  
                }
                return clazz;
            }
        }
        return null;
    }      
    
}


