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

package test.javaparser.generics.superclasses;

import java.io.IOException;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;
import test.javaparser.generics.ClassFile;

import static test.javaparser.ClassEqualizer.equalsClass;
import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.Class;


public class GenericSuperclassTest {
    
    private ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void getGenericSuperClass() throws IOException, ClassNotFoundException {   
        java.lang.Class javaClass;
        Class clazz;

        javaClass = ClassFile.class;         
        clazz = loader.loadClass("test.javaparser.ClassFile");
        equalsClass(loader,javaClass, clazz);

        javaClass = SimpleExtends.class;     

        clazz = loader.loadClass("test.javaparser.SimpleExtends");
        equalsClass(loader,javaClass, clazz);

        javaClass = SimpleGenericExtends.class;     

        clazz = loader.loadClass("test.javaparser.SimpleGenericExtends");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingularGenericNestedExtends.class;     

        clazz = loader.loadClass("test.javaparser.SingularGenericNestedExtends");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingularGenericMultipleNestedExtends.class;     

        clazz = loader.loadClass("test.javaparser.SingularGenericMultipleNestedExtends");
        equalsClass(loader,javaClass, clazz);

        javaClass = MultiGenericExtends.class;     

        clazz = loader.loadClass("test.javaparser.MultiGenericExtends");
        equalsClass(loader,javaClass, clazz);
    }

    @Test
    public void getGenericSuperClass_onWrapper() throws IOException, ClassNotFoundException {   
        java.lang.Class javaClass;
        Class clazz;

        javaClass = ClassFile.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassFile");
        equalsClass(loader,javaClass, clazz);

        javaClass = SimpleExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.SimpleExtends");
        equalsClass(loader,javaClass, clazz);

        javaClass = SimpleGenericExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.SimpleGenericExtends");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingularGenericNestedExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.SingularGenericNestedExtends");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingularGenericMultipleNestedExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.SingularGenericMultipleNestedExtends");
        equalsClass(loader,javaClass, clazz);

        javaClass = MultiGenericExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.MultiGenericExtends");
        equalsClass(loader,javaClass, clazz);
    }
    
}
