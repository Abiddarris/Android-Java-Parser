package com.abiddarris.javaparser;

import com.abiddarris.javaparser.implementations.EditableClassImpl;
import com.abiddarris.javaparser.wrappers.ClassWrapper;
import java.util.Map;
import java.util.HashMap;

public class SystemClassLoader extends ClassLoader {

    private Map<String,ClassWrapper> cache = new HashMap<>();
    
    @Override
    public Class loadClass(String name) {       
        try {
            return loadClassWrapper(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClassWrapper loadClassWrapper(String name) throws ClassNotFoundException {
        ClassWrapper classWrapper = cache.get(name);
        if(classWrapper != null) return classWrapper;
        
        classWrapper = new ClassWrapper(this,java.lang.Class.forName(name));
        cache.put(name,classWrapper);
        return classWrapper;
    }

    @Override
    public EditableClassImpl loadEditableClass(String name) {
        return null;
    }
      
}
