package com.abiddarris.javaparser.implementations;

import com.abiddarris.javaparser.Class;

public abstract class EditableClass extends Class {
   
    public abstract Import[] getImports();

    public abstract Class getInnerClass(String name);
    
}
