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

import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.EditableClass;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;
import test.javaparser.DefaultClass;
import com.abiddarris.javaparser.Class;

import static test.javaparser.ClassEqualizer.equalsClass;

public class ModifierTest {
   
    private ClassLoader loader = ClassLoaderSingleton.getInstance();   
    
    @Test
    public void getModifier() throws ClassNotFoundException {
        java.lang.Class javaClass;
        Class clazz;
        javaClass = PublicClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.PublicClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = AbstractClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.AbstractClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = FinalClass.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.FinalClass");
        equalsClass(loader,javaClass, clazz);

        javaClass = Interface.class;         

        clazz = loader.loadClass("test.javaparser.modifiers.Interface");
        equalsClass(loader,javaClass, clazz);

        //BUG ???
        /*javaClass = StrictClass.class;         

         clazz = loader.loadClass("test.javaparser.modifiers.StrictClass");
         equalsClass(loader,javaClass, clazz);     */

        javaClass = DefaultClass.class;         

        clazz = loader.loadClass("test.javaparser.DefaultClass");
        equalsClass(loader,javaClass, clazz); 

        javaClass = java.lang.Class.forName("test.javaparser.classes.GetClasses$C");

        clazz = loader.loadClass("test.javaparser.classes.GetClasses$C");
        equalsClass(loader,javaClass, clazz); 
    }
    
}
