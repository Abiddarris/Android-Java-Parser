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

package test.javaparser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public class PrintHelper {
    
    public static void printlnWithIndent(Object o, int indent) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < indent; i++) {
            builder.append(" ");
        }
        System.out.println(builder.toString() + o);
    }
    
    public static void printTypeVariable(TypeVariable variable,int indent) {

        printlnWithIndent(variable.getName(),indent);
        printlnWithIndent(variable.getGenericDeclaration().getClass(),indent);
        printlnWithIndent(variable.getGenericDeclaration(),indent);
        Type[] types = variable.getBounds();
        for(Type type : types) {
            printType(type,indent + 4);
            
            System.out.println();
        }       
    }

    public static void printType(Type type, int indent) {
        printlnWithIndent(type.getTypeName(),indent);
        printlnWithIndent(type.getClass(),indent);         
        if (type instanceof ParameterizedType) {       
            ParameterizedType parameterizedType = (ParameterizedType) type;
            printParameterizedType(parameterizedType, indent);
        } else if (type instanceof TypeVariable) {               
            TypeVariable typeVariable = (TypeVariable) type;
            printTypeVariable(typeVariable,indent);           
        } else if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;  
            printWildcardType(wildcardType,indent);
        }       
    }

    public static void printWildcardType(WildcardType wildcardType, int indent) {      
        Type[] types = wildcardType.getLowerBounds();
        for(Type type : types) {
            printType(type,indent);
            
            System.out.println();
        }
        
        System.out.println();
        
        types = wildcardType.getUpperBounds();
        for(Type type : types) {
            printType(type,indent);
            
            System.out.println();
        }
    }

    private static void printParameterizedType(ParameterizedType parameterizedType, int indent) {
        printlnWithIndent(parameterizedType.getOwnerType(), indent);
        printlnWithIndent(parameterizedType.getRawType(), indent);

        Type[] types0 = parameterizedType.getActualTypeArguments();
        for (Type type0 : types0) {
            printType(type0,indent + 4);                   
            
            System.out.println();
        }            
    }
    
    public static void printPackage(Package _package, int indent) {
        printlnWithIndent(_package.getImplementationTitle(),indent);
        printlnWithIndent(_package.getImplementationVendor(),indent);
        printlnWithIndent(_package.getImplementationVersion(),indent);
        printlnWithIndent(_package.getName(),indent);            
        printlnWithIndent(_package.getSpecificationTitle(),indent);
        printlnWithIndent(_package.getSpecificationVendor(),indent);
        printlnWithIndent(_package.getSpecificationVersion(),indent);      
        printlnWithIndent(_package.isSealed(),indent);  
    }
    
}
