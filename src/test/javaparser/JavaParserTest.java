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

package test.javaparser;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.implementations.EditableClassImpl;
import java.io.IOException;
import org.junit.Test;
import test.javaparser.generics.ClassFile;
import test.javaparser.interfaces.ExtendingInterface;
import test.javaparser.interfaces.MultipleExtendingInterface;
import test.javaparser.interfaces.MultipleImplements;
import test.javaparser.interfaces.SimpleImplements;

import static test.javaparser.PrintHelper.*;
import static test.javaparser.ClassEqualizer.equalsClass;
import com.abiddarris.javaparser.implementations.EditableClass;

public class JavaParserTest {
    
    private ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void loadObject() throws IOException {
        java.lang.Class javaClass = Object.class;
        Class clazz =loader.loadClass("java.lang.Object");
        
        equalsClass(loader,javaClass,clazz);
    }
    
    @Test
    public void isInterface_true() throws IOException {
        java.lang.Class javaClass = InterfaceFile.class;
        Class clazz = loader.loadClass("test.javaparser.InterfaceFile");
        
        equalsClass(loader,javaClass, clazz);
    }

    
    public void isInterface_false() throws IOException {
        java.lang.Class javaClass = ClassFile.class;
        Class clazz = loader.loadClass("test.javaparser.ClassFile");
        
        equalsClass(loader,javaClass, clazz);
    }
    
    @Test
    public void isAnnotation_true() throws IOException {   
        java.lang.Class javaClass = AnnotationFile.class; 
        System.out.println(Integer.toBinaryString(javaClass.getModifiers()));
        
        Class clazz = loader.loadClass("test.javaparser.AnnotationFile");
        
        equalsClass(loader,javaClass, clazz);
    } 
    
    @Test
    public void getSuperClass() throws IOException {   
        java.lang.Class javaClass = ExtendingOutsidePackageFile.class;         

        Class clazz = loader.loadClass("test.javaparser.ExtendingOutsidePackageFile");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = ExtendingFile.class;     
        clazz = loader.loadClass("test.javaparser.ExtendingFile");
        equalsClass(loader,javaClass, clazz);
    }
   
    @Test
    public void getPackage() {
        java.lang.Class javaClass = JavaParserTest.class;         

        Class clazz = loader.loadClass("test.javaparser.JavaParserTest");
        equalsClass(loader,javaClass, clazz);
    }
    
    @Test
    public void getInterfaces() {      
        java.lang.Class javaClass;
        Class clazz;
        javaClass = SimpleImplements.class;         
        
        clazz = loader.loadClass("test.javaparser.interfaces.SimpleImplements");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = MultipleImplements.class;         

        clazz = loader.loadClass("test.javaparser.interfaces.MultipleImplements");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = ExtendingInterface.class;         
        clazz = loader.loadClass("test.javaparser.interfaces.ExtendingInterface");
        equalsClass(loader,javaClass, clazz);    
        
        javaClass = MultipleExtendingInterface.class;         
        clazz = loader.loadClass("test.javaparser.interfaces.MultipleExtendingInterface");   
        equalsClass(loader,javaClass, clazz);          
    }
    

    @Test
    public void getInterfaces_onWrapper() throws ClassNotFoundException {      
        java.lang.Class javaClass = SimpleImplements.class;         

        Class clazz = loader.loadClassWrapper("test.javaparser.interfaces.SimpleImplements");
        equalsClass(loader,javaClass, clazz);

        javaClass = MultipleImplements.class;         

        clazz = loader.loadClassWrapper("test.javaparser.interfaces.MultipleImplements");
        equalsClass(loader,javaClass, clazz);
        javaClass = ExtendingInterface.class;    
        clazz = loader.loadClassWrapper("test.javaparser.interfaces.ExtendingInterface");
        equalsClass(loader,javaClass, clazz);    

        javaClass = MultipleExtendingInterface.class;         
        clazz = loader.loadClassWrapper("test.javaparser.interfaces.MultipleExtendingInterface");   
        equalsClass(loader,javaClass, clazz);        
    }
       


    @Test
    public void importTest() {
        java.lang.Class javaClass = JavaParserTest.class;         

        EditableClass clazz = loader.loadEditableClass("test.javaparser.JavaParserTest");
        equalsClass(loader,javaClass, clazz);
    }

    /*@Test
    public void getGenericInterfaces_1() {
        java.lang.Class javaClass = SingleExtendsSingleGenericClass.class;  

        for(java.lang.reflect.Type type : javaClass.getGenericInterfaces()) {
            printType(type,0);
        }
    }
    
    @Test
    public void getInterfaces_1() {
        java.lang.Class javaClass = ExtendingInterface.class;         

        System.out.println(javaClass.getSuperclass());
        
        for(java.lang.Class clazz : javaClass.getInterfaces()) {
            System.out.println(clazz);
        }
    }
    
    @Test
    public void getPackage_1() {
        java.lang.Class clazz = JavaParserTest.class;
        java.lang.Package _package = clazz.getPackage();
        printPackage(_package,0);
    }
    
    @Test
    public void getGenericSuperClass_1() throws IOException, ClassNotFoundException {   
        java.lang.Class javaClass = SimpleGenericExtends.class;         
        printType(javaClass.getGenericSuperclass(),0);
  
    }
      
    @Test
    public void getTypeParameters_1() throws IOException {   
        java.lang.Class javaClass = ClassTypeVariable6File.class;         
        java.lang.reflect.TypeVariable[] variables = javaClass.getTypeParameters();
        for(java.lang.reflect.TypeVariable variable : variables) {   
            printTypeVariable(variable,0);        

            System.out.println();
        }
    }  */
    
}
