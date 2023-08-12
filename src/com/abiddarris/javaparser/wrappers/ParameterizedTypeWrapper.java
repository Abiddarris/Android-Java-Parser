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

package com.abiddarris.javaparser.wrappers;

import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.java.ParameterizedType;
import com.abiddarris.javaparser.java.Type;

import static com.abiddarris.javaparser.wrappers.Wrappers.*;

class ParameterizedTypeWrapper implements ParameterizedType {
    
    private String typeName;
    private Type rawType;
    private Type ownerType;
    private Type[] actualTypeArguments;
    
    ParameterizedTypeWrapper(ClassLoader loader, java.lang.reflect.ParameterizedType parameterizedType) {
        typeName = parameterizedType.getTypeName();
        rawType = wrapType(loader,parameterizedType.getRawType());
        ownerType = wrapType(loader,parameterizedType.getOwnerType());
        
        java.lang.reflect.Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.actualTypeArguments = new Type[actualTypeArguments.length];
        
        for(int i = 0; i < actualTypeArguments.length; i++) {
            this.actualTypeArguments[i] = wrapType(loader,actualTypeArguments[i]);
        }
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    @Override
    public Type getRawType() {
        return rawType;
    }

    @Override
    public Type getOwnerType() {
        return ownerType;
    }
    
}
