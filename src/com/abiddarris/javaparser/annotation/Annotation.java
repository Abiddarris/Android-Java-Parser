package com.abiddarris.javaparser.annotation;

import com.abiddarris.javaparser.Class;

public interface Annotation {
    
    boolean equals(Object p1);

    int hashCode();

    String toString();

    Class annotationType();
    
}

