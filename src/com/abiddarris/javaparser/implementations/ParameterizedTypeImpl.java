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

import com.abiddarris.javaparser.java.ParameterizedType;
import com.abiddarris.javaparser.java.Type;

class ParameterizedTypeImpl implements ParameterizedType {

    private Type rawType;
    private Type[] typeArguments;

    ParameterizedTypeImpl(Type rawType, Type[] typeArguments) {
        this.rawType = rawType;
        this.typeArguments = typeArguments;
    }
    
    @Override
    public String getTypeName() {
        StringBuilder builder = new StringBuilder();
        builder.append(rawType.getTypeName())
            .append("<");
            
        for(int i =0; i < typeArguments.length; i++) {
            Type type = typeArguments[i];
            
            builder.append(type.getTypeName());
            if(i == typeArguments.length - 1)
                continue;
            builder.append(", ");
        }

        builder.append(">");   
        return builder.toString();
    }

    @Override
    public Type[] getActualTypeArguments() {
        return typeArguments;
    }

    @Override
    public Type getRawType() {
        return rawType;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    
    
    
}
