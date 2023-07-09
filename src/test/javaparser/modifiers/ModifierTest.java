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

import static com.abiddarris.javaparser.Modifier.ABSTRACT;
import static com.abiddarris.javaparser.Modifier.FINAL;
import static com.abiddarris.javaparser.Modifier.INTERFACE;
import static com.abiddarris.javaparser.Modifier.PUBLIC;
import static com.abiddarris.javaparser.Modifier.STRICT;
import static test.javaparser.ClassEqualizer.equalsClass;
import static org.junit.Assert.assertEquals;

public class ModifierTest {
   
    private ClassLoader loader = ClassLoaderSingleton.getInstance();   
    
    @Test
    public void getModifier() throws ClassNotFoundException {
        java.lang.Class javaClass;
        Class clazz;
        javaClass = PublicClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.PublicClass");
        equalsClass(loader,javaClass, clazz);
        assertEquals(clazz.getModifiers(), PUBLIC);

        javaClass = AbstractClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.AbstractClass");
        equalsClass(loader,javaClass, clazz);
        assertEquals(clazz.getModifiers(), PUBLIC | ABSTRACT);
        
        javaClass = FinalClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.FinalClass");
        equalsClass(loader,javaClass, clazz);
        assertEquals(clazz.getModifiers(), PUBLIC | FINAL);
        
        javaClass = Interface.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.Interface");
        equalsClass(loader,javaClass, clazz);
        assertEquals(clazz.getModifiers(), PUBLIC | INTERFACE);
        
        javaClass = StrictClass.class;         
    
        clazz = loader.loadClass("test.javaparser.modifiers.StrictClass");
        equalsClass(loader,javaClass, clazz);     
        assertEquals(clazz.getModifiers(), PUBLIC | STRICT);
        
        javaClass = DefaultClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.DefaultClass");
        equalsClass(loader,javaClass, clazz); 
        assertEquals(clazz.getModifiers(), 0);      
    }
    
}
