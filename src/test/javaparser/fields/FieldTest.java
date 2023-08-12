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
 
package test.javaparser.fields;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;

import static test.javaparser.ClassEqualizer.equalsClass;

public class FieldTest {

    ClassLoader loader = ClassLoaderSingleton.getInstance();

    @Test
    public void defaultField() {
        java.lang.Class javaClass = FieldClass.class;         

        Class clazz = loader.loadClass("test.javaparser.fields.FieldClass");
        equalsClass(loader,javaClass, clazz);             
    }

    @Test
    public void test() {
        java.lang.Class javaClass = FieldClass.class;    
        for(java.lang.reflect.Field field : javaClass.getDeclaredFields()) {
            System.out.println(field.getDeclaringClass());
            System.out.println(field.toString());
        }
    }
}

