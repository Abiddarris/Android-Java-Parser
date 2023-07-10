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

package test.javaparser.generics;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;
import java.io.IOException;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;

import static test.javaparser.ClassEqualizer.equalsClass;

public class GenericTest {
    
    private ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void getTypeParameters() throws IOException {   
        java.lang.Class javaClass = ClassTypeVariableFile.class;         

        Class clazz;
        clazz= loader.loadClass("test.javaparser.generics.ClassTypeVariableFile");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable2File.class;         
        clazz = loader.loadClass("test.javaparser.generics.ClassTypeVariable2File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable4File.class;         
        clazz = loader.loadClass("test.javaparser.generics.ClassTypeVariable4File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable5File.class;         
        clazz = loader.loadClass("test.javaparser.generics.ClassTypeVariable5File");
        equalsClass(loader,javaClass, clazz);


        javaClass = ClassTypeVariable6File.class;         
        clazz = loader.loadClass("test.javaparser.generics.ClassTypeVariable6File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable7File.class;         
        clazz = loader.loadClass("test.javaparser.generics.ClassTypeVariable7File");
        equalsClass(loader,javaClass, clazz);
    }

    @Test
    public void getTypeParameters_onWrapper() throws IOException, ClassNotFoundException {   
        java.lang.Class javaClass = ClassTypeVariableFile.class;         

        Class clazz;
        clazz= loader.loadClassWrapper("test.javaparser.generics.ClassTypeVariableFile");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable2File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.generics.ClassTypeVariable2File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable4File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.generics.ClassTypeVariable4File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable5File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.generics.ClassTypeVariable5File");
        equalsClass(loader,javaClass, clazz);


        javaClass = ClassTypeVariable6File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.generics.ClassTypeVariable6File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable7File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.generics.ClassTypeVariable7File");
        equalsClass(loader,javaClass, clazz);
    }
    
}
