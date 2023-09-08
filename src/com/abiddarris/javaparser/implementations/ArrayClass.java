package com.abiddarris.javaparser.implementations;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.Package;
import com.abiddarris.javaparser.annotation.Annotation;
import com.abiddarris.javaparser.java.Field;
import com.abiddarris.javaparser.java.Type;
import com.abiddarris.javaparser.java.TypeVariable;
import com.abiddarris.javaparser.wrappers.ClassWrapper;
import java.io.Serializable;

public class ArrayClass implements EditableClass {

    private EditableClass componentType;
    private Class[] interfaces;
    private Type[] genericInterfaces;

    public ArrayClass(EditableClass componentType) {
        this.componentType = componentType;
    }
    
    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public boolean isAnnotation() {
        return componentType.isAnnotation();
    }

    @Override
    public String getName() {
        return (componentType.isArray() ? "[" : "[L") + 
            componentType.getName() + (componentType.isArray() ? "" : ";");
    }

    @Override
    public ClassLoader getClassLoader() {
        return componentType.getClassLoader();
    }

    @Override
    public TypeVariable<Class>[] getTypeParameters() {
        return new TypeVariable[0];
    }

    @Override
    public Class getSuperclass() {
        return componentType.getClassLoader()
            .loadClass("java.lang.Object");
    }

    @Override
    public Type getGenericSuperclass() {
        return componentType.getClassLoader()
            .loadClass("java.lang.Object");
    }

    @Override
    public Package getPackage() {
        return componentType.getPackage();
    }

    @Override
    public Class[] getInterfaces() {
        if(interfaces != null) return interfaces;
        
        interfaces = new Class[2];
        ClassLoader loader = componentType.getClassLoader();
        
        try {
            interfaces[0] = loader.loadClassWrapper(Cloneable.class.getName());
            interfaces[1] = loader.loadClassWrapper(Serializable.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return interfaces;
    }

    @Override
    public Type[] getGenericInterfaces() {
        if(genericInterfaces != null) return genericInterfaces;
        
        ClassLoader loader = componentType.getClassLoader();
        genericInterfaces = new Type[2];
        try {
            genericInterfaces[0] = loader.loadClassWrapper(Cloneable.class.getName());
            genericInterfaces[1] = loader.loadClassWrapper(Serializable.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return genericInterfaces;
    }

    @Override
    public Class getComponentType() {
        return componentType;
    }

    @Override
    public int getModifiers() {
        return componentType.getModifiers();
    }

    @Override
    public String getSimpleName() {
        return componentType.getSimpleName() + "[]";
    }

    @Override
    public String getTypeName() {
        return componentType.getTypeName() + "[]";
    }

    @Override
    public Class[] getDeclaredClasses() {
        return new Class[0];
    }

    @Override
    public Field[] getDeclaredFields() {
        return new Field[0];
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return new Annotation[0];
    }

    @Override
    public Import[] getImports() {
        return componentType.getImports();
    }

    @Override
    public Class getInnerClass(String name) {
        return componentType.getInnerClass(name);
    }
}
