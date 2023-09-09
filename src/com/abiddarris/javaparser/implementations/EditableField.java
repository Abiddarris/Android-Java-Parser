package com.abiddarris.javaparser.implementations;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.annotation.Annotation;
import com.abiddarris.javaparser.java.Field;

public class EditableField implements Field {
    
    private EditableClassImpl editableClass;
    private int modifier;
    private String name;
    
    EditableField(EditableClassImpl editableClass, String code) {
        this.editableClass = editableClass;
        
        int assaignment = code.indexOf("=");
        int nameStart;
        if(assaignment == -1) {
            nameStart = code.lastIndexOf(" ");
            name = code.substring(nameStart)
                .trim();          
        } else {          
            name = trimRight(code.substring(0,assaignment));                     
            nameStart = name.lastIndexOf(" ");
            name = name.substring(nameStart).trim();           
        } 
        code = code.substring(0,nameStart).trim();
            
        int startType = code.lastIndexOf(" ");
        startType = Math.max(startType,0);
        
        String type = code.substring(startType).trim();
        code = code.substring(0,startType) + " ";
        System.out.println(type);
        
        modifier = HelperClass.formatModifier(code);
        
        ClassLoader a;
    }

    private String trimRight(String string) {      
        while(string.endsWith(" ")){
            string = string.substring(0,string.length() - 1);
        }
        return string;
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
    public int getModifiers() {
        return modifier;
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        //TODO :
        return null;
    }
    
}
