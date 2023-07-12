/*******************************************************************************
 * Copyright 2023 by Abiddarris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.abiddarris.javaparser;

public interface Class extends GenericDeclaration, Type {

    /*
     @androidx.annotation.RecentlyNonNull()
     public java.lang.String toString() {}

     @androidx.annotation.RecentlyNonNull()
     public java.lang.String toGenericString() {}

     @androidx.annotation.RecentlyNonNull()
     public static java.lang.Class<?> forName(java.lang.String className) throws java.lang.ClassNotFoundException {}

     @androidx.annotation.RecentlyNonNull()
     public static java.lang.Class<?> forName(java.lang.String name, boolean initialize, java.lang.ClassLoader loader) throws java.lang.ClassNotFoundException {}

     @androidx.annotation.RecentlyNonNull()
     public native T newInstance() throws java.lang.IllegalAccessException, java.lang.InstantiationException;   

     public boolean isInstance(java.lang.Object obj) {}

     public boolean isAssignableFrom(java.lang.Class<?> cls) {}
     */

    boolean isInterface();

    /*public boolean isArray() {}

     public boolean isPrimitive() {}*/

    boolean isAnnotation() 

    /*public boolean isSynthetic() {}

     @androidx.annotation.RecentlyNonNull()*/
    String getName() 


    //@androidx.annotation.RecentlyNullable()
    ClassLoader getClassLoader();


    // @androidx.annotation.RecentlyNonNull()
    @Override
    TypeVariable<Class>[] getTypeParameters() 

     //@androidx.annotation.RecentlyNullable()
    Class getSuperclass();

    // @androidx.annotation.RecentlyNullable()
    Type getGenericSuperclass();

   //  @androidx.annotation.RecentlyNullable()
    Package getPackage()

   // @androidx.annotation.RecentlyNonNull()
    Class[] getInterfaces();

     //@androidx.annotation.RecentlyNonNull()
    Type[] getGenericInterfaces();

     //@androidx.annotation.RecentlyNullable()
     //public java.lang.Class<?> getComponentType() {}

    int getModifiers();

     /*@androidx.annotation.RecentlyNullable()
     public java.lang.Object[] getSigners() {}

     @androidx.annotation.RecentlyNullable()
     public java.lang.reflect.Method getEnclosingMethod() {}

     @androidx.annotation.RecentlyNullable()
     public java.lang.reflect.Constructor<?> getEnclosingConstructor() {}

     @androidx.annotation.RecentlyNullable()
     public native java.lang.Class<?> getDeclaringClass();

     @androidx.annotation.RecentlyNullable()
     public native java.lang.Class<?> getEnclosingClass();*/

     //@androidx.annotation.RecentlyNonNull()
     String getSimpleName();

     //@androidx.annotation.RecentlyNonNull()
     @Override
     String getTypeName();

    /* @androidx.annotation.RecentlyNullable()
     public java.lang.String getCanonicalName() {}

     public native boolean isAnonymousClass();

     public boolean isLocalClass() {}

     public boolean isMemberClass() {}

     @androidx.annotation.RecentlyNonNull()
     Class[] getClasses();

     /*@androidx.annotation.RecentlyNonNull()
     public java.lang.reflect.Field[] getFields() throws java.lang.SecurityException {}

     /*@androidx.annotation.RecentlyNonNull()
     public java.lang.reflect.Method[] getMethods() throws java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()
     public java.lang.reflect.Constructor<?>[] getConstructors() throws java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()
     public java.lang.reflect.Field getField(java.lang.String name) throws java.lang.NoSuchFieldException {}

     @androidx.annotation.RecentlyNonNull()
     public transient java.lang.reflect.Method getMethod(java.lang.String name, java.lang.Class<?>...parameterTypes) throws java.lang.NoSuchMethodException, java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()
     public transient java.lang.reflect.Constructor<T> getConstructor(java.lang.Class<?>...parameterTypes) throws java.lang.NoSuchMethodException, java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()*/
     Class[] getDeclaredClasses();

     /*@androidx.annotation.RecentlyNonNull()
     public native java.lang.reflect.Field[] getDeclaredFields();

     @androidx.annotation.RecentlyNonNull()
     public java.lang.reflect.Method[] getDeclaredMethods() throws java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()
     public java.lang.reflect.Constructor<?>[] getDeclaredConstructors() throws java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()
     public native java.lang.reflect.Field getDeclaredField(java.lang.String p1) throws java.lang.NoSuchFieldException;

     @androidx.annotation.RecentlyNonNull()
     public transient java.lang.reflect.Method getDeclaredMethod(java.lang.String name, java.lang.Class<?>...parameterTypes) throws java.lang.NoSuchMethodException, java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()
     public transient java.lang.reflect.Constructor<T> getDeclaredConstructor(java.lang.Class<?>...parameterTypes) throws java.lang.NoSuchMethodException, java.lang.SecurityException {}

     @androidx.annotation.RecentlyNullable()
     public java.io.InputStream getResourceAsStream(java.lang.String name) {}

     @androidx.annotation.RecentlyNullable()
     public java.net.URL getResource(java.lang.String name) {}

     @androidx.annotation.RecentlyNullable()
     public java.security.ProtectionDomain getProtectionDomain() {}

     public boolean desiredAssertionStatus() {}

     public boolean isEnum() {}

     @androidx.annotation.RecentlyNullable()
     public T[] getEnumConstants() {}

     @androidx.annotation.RecentlyNullable()
     public T cast(java.lang.Object obj) {}

     @androidx.annotation.RecentlyNonNull()
     public <U extends java.lang.Object> java.lang.Class<? extends U> asSubclass(java.lang.Class<U> clazz) {}

     @androidx.annotation.RecentlyNullable()
     public <A extends java.lang.annotation.Annotation> A getAnnotation(java.lang.Class<A> annotationClass) {}

     public boolean isAnnotationPresent(java.lang.Class<? extends java.lang.annotation.Annotation> annotationClass) {}

     @androidx.annotation.RecentlyNonNull()
     public <A extends java.lang.annotation.Annotation> A[] getAnnotationsByType(java.lang.Class<A> annotationClass) {}

     @androidx.annotation.RecentlyNonNull()
     public java.lang.annotation.Annotation[] getAnnotations() {}

     @androidx.annotation.RecentlyNullable()
     public native <A extends java.lang.annotation.Annotation> A getDeclaredAnnotation(java.lang.Class<A> p1);

     @androidx.annotation.RecentlyNonNull()
     public native java.lang.annotation.Annotation[] getDeclaredAnnotations();

     */

}
