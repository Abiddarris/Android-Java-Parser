package com.abiddarris.javaparser.implementations;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.annotation.Annotation;
import com.abiddarris.javaparser.java.Field;

public class EditableField implements Field {
    
    private EditableClass editableClass;
    private String name;
    
    EditableField(EditableClass editableClass, String code) {
        this.editableClass = editableClass;
        
        int assaignment = code.indexOf("=");
        if(assaignment == -1) {
            name = code.substring(code.lastIndexOf(" "))
                .trim();
        } else {
            name = code.substring(0,assaignment)
                .trim();
            name = name.substring(name.lastIndexOf(" "))
                .trim();
        }  
        ClassLoader a;
    }

    @Override
    public Class getDeclaringClass() {
        return editableClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        //TODO :
        return null;
    }
    
}
