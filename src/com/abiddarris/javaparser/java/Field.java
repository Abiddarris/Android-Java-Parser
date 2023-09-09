package com.abiddarris.javaparser.java;

import com.abiddarris.javaparser.Class;

public interface Field extends AccessibleObject, Member {
 
    //@androidx.annotation.RecentlyNonNull()
    Class getDeclaringClass();
    
   //@androidx.annotation.RecentlyNonNull()
    String getName();

    int getModifiers();

    /*public boolean isEnumConstant() {}

    public boolean isSynthetic() {}

    @androidx.annotation.RecentlyNonNull()
    public java.lang.Class<?> getType() {}

    @androidx.annotation.RecentlyNonNull()
    public java.lang.reflect.Type getGenericType() {}

    public boolean equals(java.lang.Object obj) {}

    public int hashCode() {}

    @androidx.annotation.RecentlyNonNull()
    public java.lang.String toString() {}

    @androidx.annotation.RecentlyNonNull()
    public java.lang.String toGenericString() {}

    @androidx.annotation.RecentlyNullable()
    public native java.lang.Object get(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native boolean getBoolean(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native byte getByte(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native char getChar(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native short getShort(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native int getInt(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native long getLong(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native float getFloat(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native double getDouble(java.lang.Object p1) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void set(java.lang.Object p1, java.lang.Object p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void setBoolean(java.lang.Object p1, boolean p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void setByte(java.lang.Object p1, byte p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void setChar(java.lang.Object p1, char p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void setShort(java.lang.Object p1, short p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void setInt(java.lang.Object p1, int p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void setLong(java.lang.Object p1, long p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void setFloat(java.lang.Object p1, float p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    public native void setDouble(java.lang.Object p1, double p2) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException;

    @androidx.annotation.RecentlyNullable()
    public <T extends java.lang.annotation.Annotation> T getAnnotation(java.lang.Class<T> annotationClass) {}

    public <T extends java.lang.annotation.Annotation> T[] getAnnotationsByType(java.lang.Class<T> annotationClass) {}

    public boolean isAnnotationPresent(java.lang.Class<? extends java.lang.annotation.Annotation> annotationType) {}

    public native java.lang.annotation.Annotation[] getDeclaredAnnotations();
*/
}

