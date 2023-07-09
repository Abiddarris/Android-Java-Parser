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
import com.abiddarris.javaparser.EditableClass;
import com.abiddarris.javaparser.Modifier;
import com.abiddarris.javaparser.Package;
import com.abiddarris.javaparser.ParameterizedType;
import com.abiddarris.javaparser.Type;
import com.abiddarris.javaparser.TypeVariable;
import com.abiddarris.javaparser.WildcardType;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import test.javaparser.classes.GetClasses;
import test.javaparser.genericinterfaces.ExtendsAndImplements;
import test.javaparser.genericinterfaces.MultiImplementsOnlySingleGenericClass;
import test.javaparser.genericinterfaces.SingleExtendsSingleGenericClass;
import test.javaparser.genericinterfaces.SingleImplementsSingleGenericClass;
import test.javaparser.genericinterfaces.SingleImplementsSingleGenericType;
import test.javaparser.interfaces.ExtendingInterface;
import test.javaparser.interfaces.MultipleExtendingInterface;
import test.javaparser.interfaces.MultipleImplements;
import test.javaparser.interfaces.SimpleImplements;
import test.javaparser.modifiers.AbstractClass;
import test.javaparser.modifiers.FinalClass;
import test.javaparser.modifiers.Interface;
import test.javaparser.modifiers.PublicClass;

import static test.javaparser.ClassEqualizer.equalsClass;
import static test.javaparser.PrintHelper.*;

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
    public void getTypeParameters() throws IOException {   
        java.lang.Class javaClass = ClassTypeVariableFile.class;         
        
        Class clazz;
        clazz= loader.loadClass("test.javaparser.ClassTypeVariableFile");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = ClassTypeVariable2File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable2File");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = ClassTypeVariable4File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable4File");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = ClassTypeVariable5File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable5File");
        equalsClass(loader,javaClass, clazz);
        
        
        javaClass = ClassTypeVariable6File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable6File");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = ClassTypeVariable7File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable7File");
        equalsClass(loader,javaClass, clazz);
    }
    
    @Test
    public void getTypeParameters_onWrapper() throws IOException, ClassNotFoundException {   
        java.lang.Class javaClass = ClassTypeVariableFile.class;         

        Class clazz;
        clazz= loader.loadClassWrapper("test.javaparser.ClassTypeVariableFile");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable2File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable2File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable4File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable4File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable5File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable5File");
        equalsClass(loader,javaClass, clazz);


        javaClass = ClassTypeVariable6File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable6File");
        equalsClass(loader,javaClass, clazz);

        javaClass = ClassTypeVariable7File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable7File");
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
    public void getGenericInterfaces() {            
        java.lang.Class javaClass;
        Class clazz;
        javaClass = SingleImplementsSingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.genericinterfaces.SingleImplementsSingleGenericClass");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = MultiImplementsOnlySingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.genericinterfaces.MultiImplementsOnlySingleGenericClass");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = SingleImplementsSingleGenericType.class;         

        clazz = loader.loadClass("test.javaparser.genericinterfaces.SingleImplementsSingleGenericType");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = SingleExtendsSingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.genericinterfaces.SingleExtendsSingleGenericClass");
        equalsClass(loader,javaClass, clazz);
    }
    
    @Test
    public void getGenericInterfaces_onWrapper() throws ClassNotFoundException {            
        java.lang.Class javaClass;
        Class clazz;
        javaClass = SingleImplementsSingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.SingleImplementsSingleGenericClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = MultiImplementsOnlySingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.MultiImplementsOnlySingleGenericClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingleImplementsSingleGenericType.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.SingleImplementsSingleGenericType");
        equalsClass(loader,javaClass, clazz);

        javaClass = SingleExtendsSingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.SingleExtendsSingleGenericClass");
        equalsClass(loader,javaClass, clazz);
        
        javaClass = ExtendsAndImplements.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.ExtendsAndImplements");
        equalsClass(loader,javaClass, clazz);
    }

    @Test
    public void importTest() {
        java.lang.Class javaClass = JavaParserTest.class;         

        EditableClass clazz = loader.loadEditableClass("test.javaparser.JavaParserTest");
        equalsClass(loader,javaClass, clazz);
    }

    @Test
    public void getClasses() {
        java.lang.Class javaClass;
        Class clazz;
        javaClass = GetClasses.class;         
        
        clazz = loader.loadClass("test.javaparser.classes.GetClasses");
        equalsClass(loader,javaClass, clazz);         
    }
    
    
    @Test
    public void getClassName() throws ClassNotFoundException {
        java.lang.Class clazz = java.lang.Class.forName("test.javaparser.classes.GetClasses$E");
        System.out.println(clazz.getModifiers());
        System.out.println( clazz.getModifiers());
        System.out.println(java.lang.reflect.Modifier.toString(clazz.getModifiers()));
        
            
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
