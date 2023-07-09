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

public class Modifier {
    
    public static final int ABSTRACT = 1024;
    
    public static final int ANNOTATION = 8192;

    public static final int FINAL = 16;

    public static final int INTERFACE = 512;
//
//    public static final int NATIVE = 256;
//
//    public static final int PRIVATE = 2;
//
//    public static final int PROTECTED = 4;
//
    public static final int PUBLIC = 1;
    
    public static final int STATIC = 8;
//
    public static final int STRICT = 2048;
//
    public static final int SYNCHRONIZED = 32;
//
//    public static final int TRANSIENT = 128;
//
//    public static final int VOLATILE = 64;

    public static boolean isPublic(int mod) {
        return (mod & PUBLIC) != 0;
    }

//    public static boolean isPrivate(int mod) {}
//
//    public static boolean isProtected(int mod) {}
//
    public static boolean isStatic(int mod) {
        return (mod & STATIC) != 0;
    }

    public static boolean isFinal(int mod) {
        return (mod & FINAL) != 0;
    }

    public static boolean isSynchronized(int mod) {
        return (mod & SYNCHRONIZED) != 0;
    }
//
//    public static boolean isVolatile(int mod) {}
//
//    public static boolean isTransient(int mod) {}
//
//    public static boolean isNative(int mod) {}
//
    public static boolean isInterface(int mod) {
        return (mod & INTERFACE) != 0;
    }

    public static boolean isAbstract(int mod) {
        return (mod & ABSTRACT) != 0;
    }

    public static boolean isStrict(int mod) {
        return (mod & STRICT) != 0;
    }

    public static String toString(int mod) {
        StringBuilder builder = new StringBuilder();
        if(isPublic(mod))
            builder.append("public ");
        if(isStatic(mod)) 
            builder.append("static ");          
        if(isSynchronized(mod)) 
            builder.append("synchronized ");
        if(isStrict(mod))    
            builder.append("strictfp ");                
        if(isFinal(mod)) 
            builder.append("final ");               
        if(isAbstract(mod))                    
            builder.append("abstract ");
            
        if(isInterface(mod)) 
            builder.append("interface ");
        
        return builder.toString()
             .trim();
    }

      //public static int classModifiers() {}

//    public static int interfaceModifiers() {}
//
//    public static int constructorModifiers() {}
//
//    public static int methodModifiers() {}
//
//    public static int fieldModifiers() {}
//
//    public static int parameterModifiers() {}
//
    private Modifier() {}
}
