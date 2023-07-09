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

class Wrappers {
    
    static Type wrapType(ClassLoader loader, java.lang.reflect.Type type) {
        if (type instanceof java.lang.Class) {
            java.lang.Class clazz = (java.lang.Class) type;
             return loader.loadClass(clazz.getName());
        } else if (type instanceof java.lang.reflect.TypeVariable) {
             return new TypeVariableWrapper(loader, (java.lang.reflect.TypeVariable)type);
        } else if (type instanceof java.lang.reflect.ParameterizedType) {
            return new ParameterizedTypeWrapper(loader,(java.lang.reflect.ParameterizedType)type);
        } else if(type instanceof java.lang.reflect.WildcardType) {
            return new WildcardTypeWrapper(loader,(java.lang.reflect.WildcardType)type);
        }
        return null;
    }
    
}
