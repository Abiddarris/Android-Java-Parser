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

package test.javaparser.classes;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;

import static test.javaparser.ClassEqualizer.equalsClass;

public class GetClasses {
    
    ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void getClasses() {      
        java.lang.Class javaClass = GetClasses.class;         

        Class clazz = loader.loadClass("test.javaparser.classes.GetClasses");
        equalsClass(loader,javaClass, clazz);                      
    }  
    
    @Test
    public void loadNestedClass() throws ClassNotFoundException {
        java.lang.Class javaClass = java.lang.Class.forName("test.javaparser.classes.GetClasses$E$F");    

        Class clazz = loader.loadEditableClass("test.javaparser.classes.GetClasses$E$F");
        equalsClass(loader,javaClass, clazz);        
    }
    
    @Test
    public void loadInnerClassWithParentGeneric() throws ClassNotFoundException {
        java.lang.Class javaClass = java.lang.Class.forName("test.javaparser.classes.InnerClassWithGeneric");    

        Class clazz = loader.loadEditableClass("test.javaparser.classes.InnerClassWithGeneric");
        equalsClass(loader,javaClass, clazz);      
    }
    
    public class A {}
    
    public static class B{}
    
    protected static class C {}
    
    static class D {}
    
    private static class E {
        private static class F {}
    }
      
}
