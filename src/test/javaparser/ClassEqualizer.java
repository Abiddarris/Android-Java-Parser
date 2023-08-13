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
import com.abiddarris.javaparser.Package;
import com.abiddarris.javaparser.java.Field;
import com.abiddarris.javaparser.java.ParameterizedType;
import com.abiddarris.javaparser.java.Type;
import com.abiddarris.javaparser.java.TypeVariable;
import com.abiddarris.javaparser.java.WildcardType;

import static junit.framework.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class ClassEqualizer {
    
    public static void equalsClass(ClassLoader loader, java.lang.Class javaClass, Class clazz) {
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
        equalsInterfaces(loader,javaClass.getInterfaces(),clazz.getInterfaces());   
        equalsTypes(javaClass.getGenericInterfaces(), clazz.getGenericInterfaces());

        assertEquals(javaClass.getSimpleName(), clazz.getSimpleName());
        assertEqualsClasses(loader,javaClass.getDeclaredClasses(), clazz.getDeclaredClasses());
       
        java.lang.reflect.Field[] fields = javaClass.getDeclaredFields();
        List<java.lang.reflect.Field> javaFields = new ArrayList<>();
        for(java.lang.reflect.Field field : fields) {
            if (!(field.getName().contains("this$") || field.isSynthetic())) {
                javaFields.add(field);               
            }
        }
        
        equalsFields(javaFields.toArray(new java.lang.reflect.Field[0]), clazz.getDeclaredFields());
    }

    public static void equalsFields(java.lang.reflect.Field[] javaFields, Field[] fields) {
        assertEquals(javaFields.length, fields.length);
        for(int i = 0; i < javaFields.length; i ++) {
            equalsField(javaFields[i], fields[i]);
        }
    }
    
    public static void equalsField(java.lang.reflect.Field javaField, Field field) {
        assertEquals(javaField.getDeclaringClass().getName(), field.getDeclaringClass().getName());
        assertEquals(javaField.getName(),field.getName());
    }

    public static void assertEqualsClasses(ClassLoader loader, java.lang.Class[] classes, Class[] parserClasses) {
        assertEquals(classes.length, parserClasses.length);
        for(int i = 0; i < classes.length; i++) {
            equalsClass(loader,classes[i],parserClasses[i]);
        }
    }

    public static void equalsTypes(java.lang.reflect.Type[] genericInterfaces, Type[] parserGenericInterfaces) {
        assertEquals(genericInterfaces.length,parserGenericInterfaces.length);
        for(int i = 0; i < genericInterfaces.length; i++) {
            equalsType(genericInterfaces[i],parserGenericInterfaces[i]);
        }
    }

    public static void equalsInterfaces(ClassLoader loader, java.lang.Class[] interfaces, Class[] parserInterfaces) {
        assertEquals(interfaces.length,parserInterfaces.length);
        for(int i = 0; i < interfaces.length; i++) {
            equalsClass(loader,interfaces[i],parserInterfaces[i]);
        }
    }

    public static void equalsPackage(java.lang.Package _package, Package parserPackage){
        assertEquals(_package == null, parserPackage == null);
        assertEquals(_package.getName(),parserPackage.getName());
    }

    public static void equalsTypeVariables(java.lang.reflect.TypeVariable<java.lang.Class>[] typeParameters, TypeVariable<Class>[] parserTypeParameters) {
        assertEquals(typeParameters.length, parserTypeParameters.length);
        for(int i = 0; i < typeParameters.length; i++) {
            java.lang.reflect.TypeVariable<java.lang.Class> variable = typeParameters[i];
            TypeVariable<Class> parserVariable = parserTypeParameters[i];

            equalsTypeVariable(variable, parserVariable);          
        }
    }

    public static void equalsTypeVariable(java.lang.reflect.TypeVariable<java.lang.Class> variable, TypeVariable<Class> parserVariable) {
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

    public static void equalsType(java.lang.reflect.Type type, Type parserType) {
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

    public static void equalsWildcardType(java.lang.reflect.WildcardType wildcardType, WildcardType parserWildcardType) {
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

    public static Object getClassSimpleName(Object obj) {
        String name = obj.getClass().getSimpleName();
        if(name.endsWith("Class") || name.startsWith("Class"))
            return name.replace("Wrapper", "")
                .replace("Editable", "");

        return name.replace("Wrapper", "Impl");      
    }

    public static void equalsParameterizedType(java.lang.reflect.ParameterizedType type, ParameterizedType parserType) {
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
    
    
}
