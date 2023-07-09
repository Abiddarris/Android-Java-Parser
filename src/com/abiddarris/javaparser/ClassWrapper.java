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

import java.io.IOException;

import static com.abiddarris.javaparser.Wrappers.*;

class ClassWrapper implements Class {
    
    private ClassLoader loader;
    private java.lang.Class clazz;

    ClassWrapper(ClassLoader loader, java.lang.Class clazz) {
        this.loader = loader;
        this.clazz = clazz;
    }

    @Override
    public boolean isInterface() {
        return clazz.isInterface();
    }

    @Override
    public boolean isAnnotation() {
        return clazz.isAnnotation();
    }

    @Override
    public String getName() {
        return clazz.getName();
    }

    @Override
    public ClassLoader getClassLoader() {
        return loader;
    }   
    
    @Override
    public TypeVariable<Class>[] getTypeParameters() {
        java.lang.reflect.TypeVariable[] variables = clazz.getTypeParameters();
        TypeVariable<Class>[] typeVariables = new TypeVariable[variables.length];
        for(int i = 0; i < variables.length; i++) {
            java.lang.reflect.TypeVariable variable = variables[i];
            typeVariables[i] = new TypeVariableWrapper<Class>(getClassLoader(),variable);           
        }
        return typeVariables;
    }

    @Override
    public Class getSuperclass() {
        java.lang.Class clazz = this.clazz.getSuperclass();
        if(clazz == null)
            return null;
            
        return getClassLoader()
            .loadClass(clazz.getName());
    }

    @Override
    public Type getGenericSuperclass() {
        return wrapType(getClassLoader(),clazz.getGenericSuperclass());
    }

    @Override
    public Package getPackage() {
        return new PackageWrapper(clazz.getPackage());
    }

    @Override
    public Class[] getInterfaces() {
        java.lang.Class[] interfaces = clazz.getInterfaces();
        Class[] wrappedInterfaces = new Class[interfaces.length];
        
        for(int i = 0; i < interfaces.length; i++) {
            wrappedInterfaces[i] = new ClassWrapper(getClassLoader(),interfaces[i]);
        }
        
        return wrappedInterfaces;
    }

    @Override
    public Type[] getGenericInterfaces() {
        java.lang.reflect.Type[] types = clazz.getGenericInterfaces();
        Type[] wrappedTypes = new Type[types.length];
        
        for(int i = 0; i < types.length; i++) {
            wrappedTypes[i] = wrapType(getClassLoader(),types[i]);
        }
        
        return wrappedTypes;
    }

    @Override
    public int getModifiers() {
        return clazz.getModifiers();
    }

    @Override
    public String getSimpleName() {
        return clazz.getSimpleName();
    }
    
    @Override
    public String getTypeName() {
        return clazz.getTypeName();
    }

    @Override
    public Class[] getClasses() {
        return new Class[0];
    }

    
    
}
