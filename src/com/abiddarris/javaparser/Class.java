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

import com.abiddarris.javaparser.java.AnnotatedElement;
import com.abiddarris.javaparser.java.Field;
import com.abiddarris.javaparser.java.GenericDeclaration;
import com.abiddarris.javaparser.java.Type;
import com.abiddarris.javaparser.java.TypeVariable;

public abstract class Class implements GenericDeclaration, Type, AnnotatedElement {

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
    public abstract boolean isInterface();

    public abstract boolean isArray();

    public abstract boolean isPrimitive();

    public abstract boolean isAnnotation() 

    /*public boolean isSynthetic() {}

     @androidx.annotation.RecentlyNonNull()*/
    public abstract String getName() 

    //@androidx.annotation.RecentlyNullable()
    public abstract ClassLoader getClassLoader();

    // @androidx.annotation.RecentlyNonNull()
    @Override
    public abstract TypeVariable<Class>[] getTypeParameters() 

     //@androidx.annotation.RecentlyNullable()
    public abstract Class getSuperclass();

    // @androidx.annotation.RecentlyNullable()
    public abstract Type getGenericSuperclass();

   //  @androidx.annotation.RecentlyNullable()
    public abstract Package getPackage()

   // @androidx.annotation.RecentlyNonNull()
    public abstract Class[] getInterfaces();

     //@androidx.annotation.RecentlyNonNull()
    public abstract Type[] getGenericInterfaces();

     //@androidx.annotation.RecentlyNullable()
    public abstract Class getComponentType();
    
    public abstract int getModifiers();

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
     public abstract String getSimpleName();

     //@androidx.annotation.RecentlyNonNull()
     @Override
     public abstract String getTypeName();

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
     public abstract Class[] getDeclaredClasses();

     //@androidx.annotation.RecentlyNonNull()
     public abstract Field[] getDeclaredFields();

     /*@androidx.annotation.RecentlyNonNull()
     public java.lang.reflect.Method[] getDeclaredMethods() throws java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()
     public java.lang.reflect.Constructor<?>[] getDeclaredConstructors() throws java.lang.SecurityException {}

     @androidx.annotation.RecentlyNonNull()*/  
     public Field getDeclaredField(String name) throws NoSuchFieldException {
         for(Field field : getDeclaredFields()) {
             if(field.getName().equals(name)) {
                 return field;
             }
         }
         throw new NoSuchFieldException(name);
     }

     /*@androidx.annotation.RecentlyNonNull()
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
