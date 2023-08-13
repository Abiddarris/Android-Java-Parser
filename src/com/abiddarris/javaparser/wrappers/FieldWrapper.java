package com.abiddarris.javaparser.wrappers;

import com.abiddarris.javaparser.java.Field;
import com.abiddarris.javaparser.annotation.Annotation;
import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;

public class FieldWrapper implements Field {

    private ClassWrapper classWrapper;
    private java.lang.reflect.Field field;

    FieldWrapper(ClassWrapper loader, java.lang.reflect.Field field) {
        this.classWrapper = loader;
        this.field = field;
    }
    
    @Override
    public Class getDeclaringClass() {
        return classWrapper;
    }

    @Override
    public String getName() {
        return field.getName();
    }  
       
    @Override
    public Annotation[] getDeclaredAnnotations() {
        //TODO :
        return null;
    }
    
}
