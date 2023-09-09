package com.abiddarris.javaparser.implementations;

import static com.abiddarris.javaparser.java.Modifier.*;

public class HelperClass {
    
    static int formatModifier(String classInfo) {
        int modifiers = 0;
        
        if (classInfo.contains("public ")) modifiers |= PUBLIC;      
        if (classInfo.contains("abstract ")) modifiers |= ABSTRACT;    
        if (classInfo.contains("final ")) modifiers |= FINAL; 
        if (classInfo.contains("interface ")) modifiers |= INTERFACE;
        if (classInfo.contains("strictfp ")) modifiers |= STRICT;
        if (classInfo.contains("static ")) modifiers |= STATIC;    
        if (classInfo.contains("protected ")) modifiers |= PROTECTED;    
        if (classInfo.contains("private ")) modifiers |= PRIVATE;
        if (classInfo.contains("transient ")) modifiers |= TRANSIENT;
        if (classInfo.contains("volatile ")) modifiers |= VOLATILE;
        
        return modifiers;
    }
    
    
}
