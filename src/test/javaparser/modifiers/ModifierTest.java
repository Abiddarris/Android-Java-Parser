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
 
package test.javaparser.modifiers;

import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;

import static com.abiddarris.javaparser.java.Modifier.ABSTRACT;
import static com.abiddarris.javaparser.java.Modifier.FINAL;
import static com.abiddarris.javaparser.java.Modifier.INTERFACE;
import static com.abiddarris.javaparser.java.Modifier.PROTECTED;
import static com.abiddarris.javaparser.java.Modifier.PRIVATE;
import static com.abiddarris.javaparser.java.Modifier.PUBLIC;
import static com.abiddarris.javaparser.java.Modifier.STATIC;
import static com.abiddarris.javaparser.java.Modifier.STRICT;
import static test.javaparser.ClassEqualizer.equalsClass;
import static org.junit.Assert.assertEquals;

public class ModifierTest {
   
    private ClassLoader loader = ClassLoaderSingleton.getInstance();   
    
    @Test
    public void getPublic() {
        java.lang.Class javaClass = PublicClass.class;         

        Class clazz = loader.loadClass("test.javaparser.modifiers.PublicClass");
        equalsClass(loader,javaClass, clazz);
        assertEquals(clazz.getModifiers(), PUBLIC);
    }
    
    @Test
    public void getAbstract() {
        java.lang.Class javaClass = AbstractClass.class;         

        Class clazz = loader.loadClass("test.javaparser.modifiers.AbstractClass");
        equalsClass(loader,javaClass, clazz);
        assertEquals(clazz.getModifiers(), PUBLIC | ABSTRACT);
        
    }
    
    @Test
    public void getFinal() {
        java.lang.Class javaClass = FinalClass.class;         

        Class clazz = loader.loadClass("test.javaparser.modifiers.FinalClass");
        equalsClass(loader,javaClass, clazz);
        assertEquals(clazz.getModifiers(), PUBLIC | FINAL);
    }
    
    @Test
    public void getInterface() {
        java.lang.Class javaClass = Interface.class;         

        Class clazz = loader.loadClass("test.javaparser.modifiers.Interface");
        equalsClass(loader,javaClass, clazz);
        assertEquals(clazz.getModifiers(), PUBLIC | INTERFACE);
    }
    
    @Test
    public void getStrict() {
        java.lang.Class javaClass = StrictClass.class;         

        Class clazz = loader.loadClass("test.javaparser.modifiers.StrictClass");
        equalsClass(loader,javaClass, clazz);     
        assertEquals(clazz.getModifiers(), PUBLIC | STRICT);
    }
    
    @Test
    public void getDefault() throws ClassNotFoundException {
        java.lang.Class javaClass = DefaultClass.class;         

        Class clazz = loader.loadClass("test.javaparser.modifiers.DefaultClass");
        equalsClass(loader,javaClass, clazz); 
        assertEquals(clazz.getModifiers(), 0);                  
    }
    
    @Test
    public void getStatic() throws ClassNotFoundException {
        java.lang.Class javaClass = java.lang.Class.forName("test.javaparser.modifiers.ModifierTest$A");   

        Class clazz = loader.loadEditableClass("test.javaparser.modifiers.ModifierTest$A");
        equalsClass(loader,javaClass, clazz); 
        assertEquals(PUBLIC | STATIC, clazz.getModifiers());         
    }
    
    @Test
    public void getProtected() throws ClassNotFoundException {
        java.lang.Class javaClass = java.lang.Class.forName("test.javaparser.modifiers.ModifierTest$B");   

        Class clazz = loader.loadEditableClass("test.javaparser.modifiers.ModifierTest$B");
        equalsClass(loader,javaClass, clazz); 
        assertEquals(STATIC | PROTECTED, clazz.getModifiers());         
    }
    
    @Test
    public void getPrivate() throws ClassNotFoundException {
        java.lang.Class javaClass = java.lang.Class.forName("test.javaparser.modifiers.ModifierTest$C");   

        Class clazz = loader.loadEditableClass("test.javaparser.modifiers.ModifierTest$C");
        equalsClass(loader,javaClass, clazz); 
        assertEquals(STATIC | PRIVATE, clazz.getModifiers());         
    }
    
    @Test
    public void defaultField() {
        
    }
    
    public static class A{}

    protected static class B {}

    private static class C {}

}
