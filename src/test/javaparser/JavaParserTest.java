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

import static junit.framework.Assert.*;
import static test.javaparser.PrintHelper.*;

public class JavaParserTest {
    
    private ClassLoader loader = new ClassLoader(new File("/storage/emulated/0/User Data[Abiddarris]/Programming/apis/GrettaLibrary/src"));
    private static final File parent = new File("/storage/emulated/0/User Data[Abiddarris]/Programming/apis/GrettaLibrary/src/test/javaparser");
    
    //@Rule public SingleTestRule test = new SingleTestRule("getClasses");
    
    @Test
    public void loadObject() throws IOException {
        java.lang.Class javaClass = Object.class;
        Class clazz =loader.loadClass("java.lang.Object");
        
        equals(javaClass,clazz);
    }
    
    @Test
    public void isInterface_true() throws IOException {
        java.lang.Class javaClass = InterfaceFile.class;
        Class clazz = loader.loadClass("test.javaparser.InterfaceFile");
        
        equals(javaClass, clazz);
    }

    
    public void isInterface_false() throws IOException {
        java.lang.Class javaClass = ClassFile.class;
        Class clazz = loader.loadClass("test.javaparser.ClassFile");
        
        equals(javaClass, clazz);
    }
    
    @Test
    public void isAnnotation_true() throws IOException {   
        java.lang.Class javaClass = AnnotationFile.class; 
        System.out.println(Integer.toBinaryString(javaClass.getModifiers()));
        
        Class clazz = loader.loadClass("test.javaparser.AnnotationFile");
        
        equals(javaClass, clazz);
    }
    
    @Test
    public void getTypeParameters() throws IOException {   
        java.lang.Class javaClass = ClassTypeVariableFile.class;         
        
        Class clazz;
        clazz= loader.loadClass("test.javaparser.ClassTypeVariableFile");
        equals(javaClass, clazz);
        
        javaClass = ClassTypeVariable2File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable2File");
        equals(javaClass, clazz);
        
        javaClass = ClassTypeVariable4File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable4File");
        equals(javaClass, clazz);
        
        javaClass = ClassTypeVariable5File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable5File");
        equals(javaClass, clazz);
        
        
        javaClass = ClassTypeVariable6File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable6File");
        equals(javaClass, clazz);
        
        javaClass = ClassTypeVariable7File.class;         
        clazz = loader.loadClass("test.javaparser.ClassTypeVariable7File");
        equals(javaClass, clazz);
    }
    
    @Test
    public void getTypeParameters_onWrapper() throws IOException, ClassNotFoundException {   
        java.lang.Class javaClass = ClassTypeVariableFile.class;         

        Class clazz;
        clazz= loader.loadClassWrapper("test.javaparser.ClassTypeVariableFile");
        equals(javaClass, clazz);

        javaClass = ClassTypeVariable2File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable2File");
        equals(javaClass, clazz);

        javaClass = ClassTypeVariable4File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable4File");
        equals(javaClass, clazz);

        javaClass = ClassTypeVariable5File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable5File");
        equals(javaClass, clazz);


        javaClass = ClassTypeVariable6File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable6File");
        equals(javaClass, clazz);

        javaClass = ClassTypeVariable7File.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassTypeVariable7File");
        equals(javaClass, clazz);
    }
    
    @Test
    public void getSuperClass() throws IOException {   
        java.lang.Class javaClass = ExtendingOutsidePackageFile.class;         

        Class clazz = loader.loadClass("test.javaparser.ExtendingOutsidePackageFile");
        equals(javaClass, clazz);
        
        javaClass = ExtendingFile.class;     
        clazz = loader.loadClass("test.javaparser.ExtendingFile");
        equals(javaClass, clazz);
    }
    

    @Test
    public void getGenericSuperClass() throws IOException, ClassNotFoundException {   
        java.lang.Class javaClass;
        Class clazz;
        
        javaClass = ClassFile.class;         
        clazz = loader.loadClass("test.javaparser.ClassFile");
        equals(javaClass, clazz);
        
        javaClass = SimpleExtends.class;     

        clazz = loader.loadClass("test.javaparser.SimpleExtends");
        equals(javaClass, clazz);
        
        javaClass = SimpleGenericExtends.class;     

        clazz = loader.loadClass("test.javaparser.SimpleGenericExtends");
        equals(javaClass, clazz);
        
        javaClass = SingularGenericNestedExtends.class;     

        clazz = loader.loadClass("test.javaparser.SingularGenericNestedExtends");
        equals(javaClass, clazz);
        
        javaClass = SingularGenericMultipleNestedExtends.class;     

        clazz = loader.loadClass("test.javaparser.SingularGenericMultipleNestedExtends");
        equals(javaClass, clazz);
        
        javaClass = MultiGenericExtends.class;     

        clazz = loader.loadClass("test.javaparser.MultiGenericExtends");
        equals(javaClass, clazz);
    }
    
    @Test
    public void getGenericSuperClass_onWrapper() throws IOException, ClassNotFoundException {   
        java.lang.Class javaClass;
        Class clazz;

        javaClass = ClassFile.class;         
        clazz = loader.loadClassWrapper("test.javaparser.ClassFile");
        equals(javaClass, clazz);

        javaClass = SimpleExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.SimpleExtends");
        equals(javaClass, clazz);

        javaClass = SimpleGenericExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.SimpleGenericExtends");
        equals(javaClass, clazz);

        javaClass = SingularGenericNestedExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.SingularGenericNestedExtends");
        equals(javaClass, clazz);

        javaClass = SingularGenericMultipleNestedExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.SingularGenericMultipleNestedExtends");
        equals(javaClass, clazz);

        javaClass = MultiGenericExtends.class;     

        clazz = loader.loadClassWrapper("test.javaparser.MultiGenericExtends");
        equals(javaClass, clazz);
    }
    
    @Test
    public void getPackage() {
        java.lang.Class javaClass = JavaParserTest.class;         

        Class clazz = loader.loadClass("test.javaparser.JavaParserTest");
        equals(javaClass, clazz);
    }
    
    @Test
    public void getInterfaces() {      
        java.lang.Class javaClass;
        Class clazz;
        javaClass = SimpleImplements.class;         
        
        clazz = loader.loadClass("test.javaparser.interfaces.SimpleImplements");
        equals(javaClass, clazz);
        
        javaClass = MultipleImplements.class;         

        clazz = loader.loadClass("test.javaparser.interfaces.MultipleImplements");
        equals(javaClass, clazz);
        
        javaClass = ExtendingInterface.class;         
        clazz = loader.loadClass("test.javaparser.interfaces.ExtendingInterface");
        equals(javaClass, clazz);    
        
        javaClass = MultipleExtendingInterface.class;         
        clazz = loader.loadClass("test.javaparser.interfaces.MultipleExtendingInterface");   
        equals(javaClass, clazz);          
    }
    

    @Test
    public void getInterfaces_onWrapper() throws ClassNotFoundException {      
        java.lang.Class javaClass = SimpleImplements.class;         

        Class clazz = loader.loadClassWrapper("test.javaparser.interfaces.SimpleImplements");
        equals(javaClass, clazz);

        javaClass = MultipleImplements.class;         

        clazz = loader.loadClassWrapper("test.javaparser.interfaces.MultipleImplements");
        equals(javaClass, clazz);
        javaClass = ExtendingInterface.class;    
        clazz = loader.loadClassWrapper("test.javaparser.interfaces.ExtendingInterface");
        equals(javaClass, clazz);    

        javaClass = MultipleExtendingInterface.class;         
        clazz = loader.loadClassWrapper("test.javaparser.interfaces.MultipleExtendingInterface");   
        equals(javaClass, clazz);        
    }
       
    @Test
    public void getGenericInterfaces() {            
        java.lang.Class javaClass;
        Class clazz;
        javaClass = SingleImplementsSingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.genericinterfaces.SingleImplementsSingleGenericClass");
        equals(javaClass, clazz);
        
        javaClass = MultiImplementsOnlySingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.genericinterfaces.MultiImplementsOnlySingleGenericClass");
        equals(javaClass, clazz);
        
        javaClass = SingleImplementsSingleGenericType.class;         

        clazz = loader.loadClass("test.javaparser.genericinterfaces.SingleImplementsSingleGenericType");
        equals(javaClass, clazz);
        
        javaClass = SingleExtendsSingleGenericClass.class;         

        clazz = loader.loadClass("test.javaparser.genericinterfaces.SingleExtendsSingleGenericClass");
        equals(javaClass, clazz);
    }
    
    @Test
    public void getGenericInterfaces_onWrapper() throws ClassNotFoundException {            
        java.lang.Class javaClass;
        Class clazz;
        javaClass = SingleImplementsSingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.SingleImplementsSingleGenericClass");
        equals(javaClass, clazz);

        javaClass = MultiImplementsOnlySingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.MultiImplementsOnlySingleGenericClass");
        equals(javaClass, clazz);

        javaClass = SingleImplementsSingleGenericType.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.SingleImplementsSingleGenericType");
        equals(javaClass, clazz);

        javaClass = SingleExtendsSingleGenericClass.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.SingleExtendsSingleGenericClass");
        equals(javaClass, clazz);
        
        javaClass = ExtendsAndImplements.class;         

        clazz = loader.loadClassWrapper("test.javaparser.genericinterfaces.ExtendsAndImplements");
        equals(javaClass, clazz);
    }

    @Test
    public void importTest() {
        java.lang.Class javaClass = JavaParserTest.class;         

        EditableClass clazz = loader.loadEditableClass("test.javaparser.JavaParserTest");
        equals(javaClass, clazz);
    }
    
    @Test
    public void getModifier() throws ClassNotFoundException {
        java.lang.Class javaClass;
        Class clazz;
        javaClass = PublicClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.PublicClass");
        equals(javaClass, clazz);
        
        javaClass = AbstractClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.AbstractClass");
        equals(javaClass, clazz);
        
        javaClass = FinalClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.FinalClass");
        equals(javaClass, clazz);
        
        javaClass = Interface.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.Interface");
        equals(javaClass, clazz);
        
        //BUG ???
        /*javaClass = StrictClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.StrictClass");
        equals(javaClass, clazz);     */
        
        javaClass = DefaultClass.class;         

        clazz = loader.loadClass("test.javaparser.DefaultClass");
        equals(javaClass, clazz); 
        
        javaClass = java.lang.Class.forName("test.javaparser.classes.GetClasses$C");

        clazz = loader.loadClass("test.javaparser.classes.GetClasses$C");
        assertEquals(clazz.getClass(), EditableClass.class);
        equals(javaClass, clazz); 
    }
    
    @Test
    public void getClasses() {
        java.lang.Class javaClass;
        Class clazz;
        javaClass = GetClasses.class;         
        
        clazz = loader.loadClass("test.javaparser.classes.GetClasses");
        equals(javaClass, clazz);         
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
      
    private void equals(java.lang.Class javaClass, Class clazz) {
        assertEquals(javaClass.isInterface(), clazz.isInterface());
        assertEquals(javaClass.isAnnotation() , clazz.isAnnotation());
        assertEquals(javaClass.getName(), clazz.getName());
        assertEquals(loader, clazz.getClassLoader());
        assertEquals(javaClass.getTypeName(), clazz.getTypeName());
        equalsTypeVariables(javaClass.getTypeParameters(),clazz.getTypeParameters());
        assertEquals(javaClass.getSuperclass() != null ? javaClass.getSuperclass().getName() : null, 
            clazz.getSuperclass() != null ? clazz.getSuperclass().getName() : null);    
            
        assertEquals(javaClass.getGenericSuperclass() == null, clazz.getGenericSuperclass() == null);
        if(javaClass.getGenericSuperclass() != null)
            equalsType(javaClass.getGenericSuperclass(), clazz.getGenericSuperclass());
            
        equalsPackage(javaClass.getPackage(),clazz.getPackage());
        equalsInterfaces(javaClass.getInterfaces(),clazz.getInterfaces());   
        equalsTypes(javaClass.getGenericInterfaces(), clazz.getGenericInterfaces());
        
        assertEquals(javaClass.getModifiers(), clazz.getModifiers());
        assertEquals(java.lang.reflect.Modifier.toString(javaClass.getModifiers()), Modifier.toString(clazz.getModifiers()));
        assertEquals(javaClass.getSimpleName(), clazz.getSimpleName());
        assertEqualsClasses(javaClass.getClasses(), clazz.getClasses());
        
        
    }

    private void assertEqualsClasses(java.lang.Class[] classes, Class[] parserClasses) {
        assertEquals(classes.length, parserClasses.length);
        for(int i = 0; i < classes.length; i++) {
            equals(classes[i],parserClasses[i]);
        }
    }

    private void equalsTypes(java.lang.reflect.Type[] genericInterfaces, Type[] parserGenericInterfaces) {
        assertEquals(genericInterfaces.length,parserGenericInterfaces.length);
        for(int i = 0; i < genericInterfaces.length; i++) {
            equalsType(genericInterfaces[i],parserGenericInterfaces[i]);
        }
    }

    private void equalsInterfaces(java.lang.Class[] interfaces, Class[] parserInterfaces) {
        assertEquals(interfaces.length,parserInterfaces.length);
        for(int i = 0; i < interfaces.length; i++) {
            equals(interfaces[i],parserInterfaces[i]);
        }
    }

    private void equalsPackage(java.lang.Package _package, Package parserPackage){
        assertEquals(_package == null, parserPackage == null);
        assertEquals(_package.getName(),parserPackage.getName());
    }

    private void equalsTypeVariables(java.lang.reflect.TypeVariable<java.lang.Class>[] typeParameters, TypeVariable<Class>[] parserTypeParameters) {
        assertEquals(typeParameters.length, parserTypeParameters.length);
        for(int i = 0; i < typeParameters.length; i++) {
            java.lang.reflect.TypeVariable<java.lang.Class> variable = typeParameters[i];
            TypeVariable<Class> parserVariable = parserTypeParameters[i];
            
            equalsTypeVariable(variable, parserVariable);          
        }
    }

    private void equalsTypeVariable(java.lang.reflect.TypeVariable<java.lang.Class> variable, TypeVariable<Class> parserVariable) {
        assertEquals(variable.getClass().getSimpleName(), getClassSimpleName(parserVariable));
        assertEquals(variable.getTypeName(), parserVariable.getTypeName());
        assertEquals(variable.getGenericDeclaration().getName(), parserVariable.getGenericDeclaration().getName());         
        assertEquals(variable.getName(), parserVariable.getName());

        java.lang.reflect.Type[] types = variable.getBounds();
        Type[] parserTypes = parserVariable.getBounds();
        for (int j = 0; j < types.length; j++) {      
            java.lang.reflect.Type type = types[j];
            Type parserType = parserTypes[j];
            equalsType(type, parserType);
        }
    }

    private void equalsType(java.lang.reflect.Type type, Type parserType) {
//        assertEquals(type == null, parserType == null);
//        if(type == null) {
//            return;
//        }
        assertEquals(type.getClass().getSimpleName(), getClassSimpleName(parserType));
        assertEquals(type.getTypeName(), parserType.getTypeName());

        if (type instanceof java.lang.reflect.TypeVariable) {
            equalsTypeVariable((java.lang.reflect.TypeVariable)type, (TypeVariable)parserType);
        } else if (type instanceof java.lang.reflect.ParameterizedType) {
            equalsParameterizedType((java.lang.reflect.ParameterizedType)type, (ParameterizedType)parserType);
        } else if (type instanceof java.lang.reflect.WildcardType) {
            equalsWildcardType((java.lang.reflect.WildcardType)type, (WildcardType)parserType);
        }
    }

    private void equalsWildcardType(java.lang.reflect.WildcardType wildcardType, WildcardType parserWildcardType) {
        java.lang.reflect.Type[] lowerBounds = wildcardType.getLowerBounds();
        
        Type[] parserLowerBounds = parserWildcardType.getLowerBounds();
        assertEquals(lowerBounds.length,parserLowerBounds.length);
        
        for(int i = 0; i < lowerBounds.length; i++) {
            java.lang.reflect.Type type = lowerBounds[i];
            Type parserType = parserLowerBounds[i];
            
            equalsType(type,parserType);
        }
        
        java.lang.reflect.Type[] upperBounds = wildcardType.getUpperBounds();
        Type[] parserUpperBounds = parserWildcardType.getUpperBounds();
        
        assertEquals(upperBounds.length,parserUpperBounds.length);

        for(int i = 0; i < upperBounds.length; i++) {
            java.lang.reflect.Type type = upperBounds[i];
            Type parserType = parserUpperBounds[i];

            equalsType(type,parserType);
        }
    }

    private Object getClassSimpleName(Object obj) {
        String name = obj.getClass().getSimpleName();
        if(name.endsWith("Class") || name.startsWith("Class"))
            return name.replace("Wrapper", "")
                .replace("Editable", "");
                
        return name.replace("Wrapper", "Impl");      
    }
   
    private void equalsParameterizedType(java.lang.reflect.ParameterizedType type, ParameterizedType parserType) {
        assertEquals(type.getClass().getSimpleName(), getClassSimpleName(parserType));
        assertEquals(type.getTypeName(), parserType.getTypeName());
        assertEquals(type.getRawType().getClass().getSimpleName(), getClassSimpleName(parserType.getRawType().getClass()));
        
        equalsType(type.getRawType(),parserType.getRawType());
        
        assertEquals(type.getOwnerType(),parserType.getOwnerType());
        
        java.lang.reflect.Type[] types = type.getActualTypeArguments();
        Type[] parserTypes = parserType.getActualTypeArguments();
        
        assertEquals(types.length,parserTypes.length);
        
        for(int i = 0; i < types.length; i++) {
            equalsType(types[i],parserTypes[i]);
        }
        
    }
    
    private File findFile(String name) throws IOException {
        return new File(parent,name);
    }
}
