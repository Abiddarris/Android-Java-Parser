package com.abiddarris.javaparser.implementations;

import com.abiddarris.javaparser.Class;

public interface EditableClass extends Class {
   
    Import[] getImports();

    Class getInnerClass(String name);
    
}
