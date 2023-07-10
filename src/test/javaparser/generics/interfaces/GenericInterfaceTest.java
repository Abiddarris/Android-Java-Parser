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

package test.javaparser.generics.interfaces;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;

import static test.javaparser.ClassEqualizer.equalsClass;

public class GenericInterfaceTest {
    
    private ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void getGenericInterfaces() {            
        java.lang.Class javaClass;
        Class clazz;
        javaClass = SingleImplementsSingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.generics.interfaces.SingleImplementsSingleGenericClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = MultiImplementsOnlySingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.generics.interfaces.MultiImplementsOnlySingleGenericClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingleImplementsSingleGenericType.class;         

        clazz = loader.loadClass("test.javaparser.generics.interfaces.SingleImplementsSingleGenericType");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingleExtendsSingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.generics.interfaces.SingleExtendsSingleGenericClass");
        equalsClass(loader,javaClass, clazz);
    }

    @Test
    public void getGenericInterfaces_onWrapper() throws ClassNotFoundException {            
        java.lang.Class javaClass;
        Class clazz;
        javaClass = SingleImplementsSingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.generics.interfaces.SingleImplementsSingleGenericClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = MultiImplementsOnlySingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.generics.interfaces.MultiImplementsOnlySingleGenericClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingleImplementsSingleGenericType.class;         

        clazz = loader.loadClassWrapper("test.javaparser.generics.interfaces.SingleImplementsSingleGenericType");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingleExtendsSingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.generics.interfaces.SingleExtendsSingleGenericClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = ExtendsAndImplements.class;         

        clazz = loader.loadClassWrapper("test.javaparser.generics.interfaces.ExtendsAndImplements");
        equalsClass(loader,javaClass, clazz);
    }
}
