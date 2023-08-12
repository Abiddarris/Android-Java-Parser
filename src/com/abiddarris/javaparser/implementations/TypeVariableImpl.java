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

import com.abiddarris.javaparser.java.GenericDeclaration;
import com.abiddarris.javaparser.java.Type;
import com.abiddarris.javaparser.java.TypeVariable;

class TypeVariableImpl<D extends GenericDeclaration> implements TypeVariable<D> {

    private D genericDeclaration;
    private String name;
    private Type[] types;
    
    TypeVariableImpl(D genericDeclaration, String name) {
        this(genericDeclaration,name,null);
    }

    TypeVariableImpl(D genericDeclaration, String name, Type[] types) {
        this.genericDeclaration = genericDeclaration;
        this.name = name;
        this.types = types;
    }
    
    void setBounds(Type[] types) {
        this.types = types;
    }

    @Override
    public String getTypeName() {
        return getName();
    }

    @Override
    public Type[] getBounds() {
        return types;
    }

    @Override
    public D getGenericDeclaration() {
        return genericDeclaration;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
