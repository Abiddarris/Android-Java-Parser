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

package com.abiddarris.javaparser.java;

import com.abiddarris.javaparser.annotation.Annotation;

public abstract interface AnnotatedElement {
  
    //public boolean isAnnotationPresent(java.lang.Class<? extends java.lang.annotation.Annotation> annotationClass) {}

    //@androidx.annotation.RecentlyNullable()
    //public abstract <T extends java.lang.annotation.Annotation> T getAnnotation(java.lang.Class<T> p1);

    //@androidx.annotation.RecentlyNonNull()
    //public abstract java.lang.annotation.Annotation[] getAnnotations();

    //public <T extends java.lang.annotation.Annotation> T[] getAnnotationsByType(java.lang.Class<T> annotationClass) {}

    //@androidx.annotation.RecentlyNullable()
    //public <T extends java.lang.annotation.Annotation> T getDeclaredAnnotation(java.lang.Class<T> annotationClass) {}

    //public <T extends java.lang.annotation.Annotation> T[] getDeclaredAnnotationsByType(java.lang.Class<T> annotationClass) {}

    //@androidx.annotation.RecentlyNonNull()
    Annotation[] getDeclaredAnnotations();
}

