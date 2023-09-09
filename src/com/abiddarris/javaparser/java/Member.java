package com.abiddarris.javaparser.java;

import com.abiddarris.javaparser.Class;

public abstract interface Member {
    
    /*public static final int DECLARED = 1;

    public static final int PUBLIC = 0;

    @androidx.annotation.RecentlyNonNull()*/
    Class getDeclaringClass();

    //@androidx.annotation.RecentlyNonNull()
    String getName();

    int getModifiers();

    /*public abstract boolean isSynthetic();*/

}

