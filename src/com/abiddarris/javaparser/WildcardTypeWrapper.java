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

import static com.abiddarris.javaparser.Wrappers.*;

class WildcardTypeWrapper implements WildcardType {
    
    private String typeName;
    private Type[] lowerBounds;
    private Type[] upperBounds;
    
    WildcardTypeWrapper(ClassLoader loader, java.lang.reflect.WildcardType wildcardType) {
        typeName = wildcardType.getTypeName();
        
        java.lang.reflect.Type[] lowerBounds = wildcardType.getLowerBounds();
        this.lowerBounds = new Type[lowerBounds.length];
        for(int i = 0; i < lowerBounds.length; i++) {
            this.lowerBounds[i] = wrapType(loader,lowerBounds[i]);
        }
        
        java.lang.reflect.Type[] upperBounds = wildcardType.getUpperBounds();
        this.upperBounds = new Type[upperBounds.length];
        for(int i = 0; i < upperBounds.length; i++) {
            this.upperBounds[i] = wrapType(loader,upperBounds[i]);
        }
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    @Override
    public Type[] getUpperBounds() {
        return upperBounds;
    }

    @Override
    public Type[] getLowerBounds() {
        return lowerBounds;
    }
    
}
