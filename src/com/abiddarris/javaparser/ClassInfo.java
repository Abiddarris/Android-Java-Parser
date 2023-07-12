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

package com.abiddarris.javaparser;

import java.util.ArrayList;
import java.util.List;

import static com.abiddarris.javaparser.Modifier.ABSTRACT;
import static com.abiddarris.javaparser.Modifier.ANNOTATION;
import static com.abiddarris.javaparser.Modifier.FINAL;
import static com.abiddarris.javaparser.Modifier.INTERFACE;
import static com.abiddarris.javaparser.Modifier.PROTECTED;
import static com.abiddarris.javaparser.Modifier.PRIVATE;
import static com.abiddarris.javaparser.Modifier.PUBLIC;
import static com.abiddarris.javaparser.Modifier.STATIC;
import static com.abiddarris.javaparser.Modifier.STRICT;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.Collections;
//import static com.abiddarris.javaparser.Modifier.SYNCHRONIZED;

class ClassInfo {
    
    private Bracket bracket;
    private EditableClass parent;
    private Class[] declaredClasses;       
    private EditableClass editableClass;
    private List<Generic> generics = new ArrayList<>();   
    private Package _package;
    private String cleanJavaInfo;
    private String code;
    private String extendInfo;
    private String javaFileInfo;
    private String[] implementsInfos;
    private Type genericSuperclassType;
    private Type[] genericInterfaces;
    private TypeVariable[] genericDeclarations;
    private TypeVariable[] typeParameters;
    
    boolean isAnnotation;
    boolean isInterface;    
    Class[] interfaces = new Class[0];  
    int modifiers;
    String name;
    String simpleName;
    String superClass;      
    
    ClassInfo(EditableClass parent, String code, Bracket bracket, EditableClass editableClass) {    
        this.parent = parent;
        this.editableClass = editableClass;
        this.code = code;
        this.bracket = bracket;            
    }
    
    private String getJavaFileInfo() {
        if(javaFileInfo == null) {
            javaFileInfo = code.substring(0,bracket.start);
            javaFileInfo = javaFileInfo.trim();
        }      
        return javaFileInfo;
    }
    
    private String getCleanJavaInfo() {
        return cleanJavaInfo;
    }
    
    Package getPackage() {
        if(_package == null) {
            String javaFileInfo = getJavaFileInfo();
            int packageIndex = javaFileInfo.indexOf("package ");
            _package = new PackageImpl(javaFileInfo.substring(packageIndex + "package ".length(), javaFileInfo.indexOf(";", packageIndex)));              
        }
        return _package;
    }
    
    void load2(Class parent, Bracket bracket, String code) {
        String before = getJavaFileInfo();

        int lastSemicolon = before.lastIndexOf(";");
        int lastCurlyBracketStart = before.lastIndexOf("{");      
        int lastCurlyBracketEnd = before.lastIndexOf("}");      
        int max = Math.max(lastCurlyBracketStart, Math.max(lastSemicolon,lastCurlyBracketEnd));

        String classInfo = before.substring(max + 1);

        isInterface = classInfo.contains(" interface ");
        isAnnotation = classInfo.contains(" @interface ");               

        if(isAnnotation) isInterface = isAnnotation;

        if(isAnnotation) {
            this.interfaces = new Class[]{editableClass.resolveClassByName("java.lang.annotation.Annotation")};
            this.genericInterfaces = new Class[]{editableClass.resolveClassByName("java.lang.annotation.Annotation")};
        }

        classInfo = encodeNestedType(classInfo,generics);
     
        int superClassStart = classInfo.indexOf(" extends ");
        if(superClassStart != -1) {                    
            int superClassEnd = classInfo.indexOf(" ", superClassStart + " extends ".length());
            if(superClassEnd == -1)
                superClassEnd = classInfo.length();                        

            if(isInterface) {                                                     
                int interfaceEnd = classInfo.length();                                  
                String implementsInfo = classInfo.substring(superClassStart, interfaceEnd);         
                classInfo = classInfo.substring(0,superClassStart);

                implementsInfos = implementsInfo.split(", ");
                this.interfaces = new Class[implementsInfos.length];

                for(int i = 0; i < implementsInfos.length; i++) {
                    this.interfaces[i] = editableClass.resolveClassByName(getClassName(implementsInfos[i]));             
                }
            } else {            
                extendInfo = classInfo.substring(superClassStart, superClassEnd);
                classInfo = classInfo.substring(0,superClassStart);
                superClass = getClassName(extendInfo);              
            }                    
        }

        int interfaceStart = classInfo.indexOf(" implements ");
        if(interfaceStart != -1) {                    
            int interfaceEnd = classInfo.length();                                  
            String implementsInfo = classInfo.substring(interfaceStart, interfaceEnd);         
            classInfo = classInfo.substring(0,interfaceStart);

            implementsInfos = implementsInfo.split(", ");
            this.interfaces = new Class[implementsInfos.length];

            for(int i = 0; i < implementsInfos.length; i++) {
                this.interfaces[i] = editableClass.resolveClassByName(getClassName(implementsInfos[i]));             
            }
        }

        cleanJavaInfo = classInfo;
        simpleName = getClassName(classInfo);   

        classInfo = classInfo.replace(simpleName,"");

        if(classInfo.contains("public ")) modifiers |= PUBLIC;      
        if(classInfo.contains("abstract ")) modifiers |= ABSTRACT;    
        if(classInfo.contains("final ")) modifiers |= FINAL; 
        if(classInfo.contains("interface ")) modifiers |= INTERFACE;
        if(classInfo.contains("strictfp ")) modifiers |= STRICT;
        if(classInfo.contains("static ")) modifiers |= STATIC;    
        if(classInfo.contains("protected ")) modifiers |= PROTECTED;    
        if(classInfo.contains("private ")) modifiers |= PRIVATE;    
        
        if(isAnnotation) modifiers |= ANNOTATION;

        if(parent != null)
            name = parent.getName() + "$" + simpleName;
        else
            name = editableClass.getPackage().getName() + "." + simpleName;                        
    }
    
    Class[] getDeclaredClasses() {
        if(declaredClasses == null) {
            Bracket lastChild = null;
            List<Class> actualClasses = new ArrayList<>();
            for(Bracket child : bracket.children) {
                int start = lastChild == null ? bracket.start + 1 : lastChild.end + 1;
                int end = child.start;          

                String info = code.substring(start,end).trim();
                if(info.contains(" class ")) {
                    ClassLoader classLoader = editableClass.getClassLoader();
                    Class clazz = new EditableClass(editableClass , editableClass.imports, classLoader, code, child);                
                    actualClasses.add(clazz);
                }             

                lastChild = child;
            }
            this.declaredClasses = actualClasses.toArray(new Class[0]);
        }
        
        return declaredClasses;
    }
    
   /* Class[] getDeclaredClasses () {
        if(classes == null) {
            Class[] actualClasses = getActualDeclaredClasses();
            List<Class> classes = new ArrayList<>();
            for(Class actualClass : actualClasses) {
                if(Modifier.isPublic(actualClass.getModifiers()))
                    classes.add(actualClass);
            }
            this.classes = classes.toArray(new Class[0]);
        }
        return classes;
    }*/
    
    TypeVariable[] getTypeParameters() {
        if(typeParameters != null) return typeParameters;     

        String classInfo = getCleanJavaInfo();
        int genericStart = classInfo.indexOf("[");
        int genericEnd = classInfo.indexOf("]");
        if(genericStart == -1 || genericEnd == -1) {
            return typeParameters = new TypeVariable[0];
        } 
        
        String data = getDecodedNestedType(generics,classInfo);             
        List<Generic> generics = new ArrayList<>();
        data = encodeNestedType(data,generics);    

        String[] genericDeclarations = data.split(",");     

        TypeVariable[] typeParameters = new TypeVariable[genericDeclarations.length];
        String[] typesString = new String[genericDeclarations.length];

        for(int i = 0; i < genericDeclarations.length; i++) {
            String genericDeclaration = decodeNestedType(generics,genericDeclarations[i])
                .replace("<","[")
                .replace(">","]");
            String typeWithGenericDeclaration, name;          
            int startExtends = genericDeclaration.indexOf(" extends ");
            if(startExtends == -1) {
                name = genericDeclaration.trim();
                typeWithGenericDeclaration = "java.lang.Object";         
            } else {
                name = genericDeclaration.substring(0,startExtends + 1)
                    .trim();
                typeWithGenericDeclaration = genericDeclaration.substring(startExtends + " extends ".length());               
            }

            TypeVariable<Class> variable = new TypeVariableImpl<Class>(
                editableClass,name);
            typesString[i] = typeWithGenericDeclaration;
            typeParameters[i] = variable;
        }

        for(int i = 0; i < typesString.length; i++) {
            String typeNameWithGenericDeclaration = typesString[i];       
            String typeName = getClassName(typeNameWithGenericDeclaration);

            Type type;
            Type[] types = getGeneric(typeParameters,typeNameWithGenericDeclaration);
            type = resolveTypeByName(typeParameters,typeName);
            if(types.length != 0) {                           
                type = new ParameterizedTypeImpl(type,types);
            }

            ((TypeVariableImpl)typeParameters[i]).setBounds(
                new Type[]{type});
        }
        
        return this.typeParameters = typeParameters;
    }

    TypeVariable[] getGenericDeclaration() {
        if(genericDeclarations != null) return genericDeclarations;
        TypeVariable[] typeParameters = getTypeParameters();
        
        if(typeParameters.length == 0 && parent != null) return genericDeclarations = parent.getClassInfo()
            .getGenericDeclaration();
        
        if(parent != null) {
            List<TypeVariable> typeVariables = new ArrayList<>();
            for(TypeVariable typeVariable :  typeParameters) {
                typeVariables.add(typeVariable);
            }

            for(TypeVariable typeVariable : parent.getClassInfo().getGenericDeclaration()) {
                boolean found = false;
                for(TypeVariable variable : typeVariables) {
                    if(typeVariable.getName().equals(variable.getName())) {
                        found = true;
                        break;
                    }                    
                }
                if(!found) {
                    typeVariables.add(typeVariable);
                }
            }
            return this.genericDeclarations = typeVariables.toArray(new TypeVariable[0]);
        }
          
        return this.genericDeclarations = typeParameters;
    }
    
    Type getGenericSuperclass() {      
        if(extendInfo != null && !isInterface) {       
            if(genericSuperclassType != null) return genericSuperclassType;
            
            extendInfo = decodeNestedType(generics,extendInfo)
                .replace("<","[")
                .replace(">","]");

            TypeVariable[] genericDeclaration = getGenericDeclaration();
            Type[] types = getGeneric(genericDeclaration, extendInfo);
            Type rawType = resolveTypeByName(getGenericDeclaration(),superClass);
            if(types.length != 0) {                           
                return genericSuperclassType = new ParameterizedTypeImpl(rawType,types);
            }
        }
        return null;
    }
    
    Type[] getGenericInterfaces() {
        if(genericInterfaces != null) return genericInterfaces;
        
        if(implementsInfos != null){
            genericInterfaces = new Type[implementsInfos.length];
            for(int i = 0; i < implementsInfos.length; i++) {
                String implementsInfo = implementsInfos[i];
                implementsInfo = decodeNestedType(generics,implementsInfo)
                    .replace("<","[")
                    .replace(">","]");

                Type[] types = getGeneric(getGenericDeclaration(),implementsInfo);
                Type rawType = resolveTypeByName(getGenericDeclaration(),interfaces[i].getName());
                if(types.length != 0 && isInterface) {      
                    genericInterfaces = new Type[0];
                    break;
                } else if(types.length != 0) {
                    genericInterfaces[i] = new ParameterizedTypeImpl(rawType,types);
                } else {
                    genericInterfaces[i] = interfaces[i];
                }
            }
        }
        return genericInterfaces == null ? genericInterfaces = new Type[0]: genericInterfaces;
    }
    
    private String encodeNestedType(String classInfo, List<Generic> generics) {
        StringBuilder builder = new StringBuilder(classInfo);
        Bracket parent = Bracket.parse(classInfo, "<", ">");
        for (Bracket bracket : parent.children) {
            Generic generic = new Generic(bracket, classInfo.substring(bracket.start + 1, bracket.end));
            generics.add(generic);

            String key = "[" + generic.key + "]";

            for (int i = bracket.start; i <= bracket.end; i++) {
                builder.setCharAt(i, key.charAt(i - bracket.start));
            }
        }
        return builder.toString();
    }

    private String getClassName(String classInfo) {      
        int start,end;
        int genericStart = classInfo.indexOf("[");
        int genericEnd = classInfo.indexOf("]");
        if(genericStart == -1 || genericEnd == -1) {
            start = classInfo.lastIndexOf(" ") + 1;
            end = classInfo.length();          

            return classInfo.substring(start,end);
        }

        start = classInfo.lastIndexOf(" ", genericStart) + 1;
        end = genericStart;

        return classInfo.substring(start,end);      
    }  

    private String decodeNestedType(List<Generic> generics, String classInfo) {
        int genericStart = classInfo.indexOf("[");
        int genericEnd = classInfo.indexOf("]");

        if(genericStart == -1 || genericEnd == -1) {
            return classInfo;
        }

        Generic generic = getDecodedNestedTypeGeneric(generics,classInfo);       
        return classInfo.replace("[" + generic.key + "]","<" + generic.data + ">");
    }

    private String getDecodedNestedType(List<Generic> generics, String classInfo) {
        int genericStart = classInfo.indexOf("[");
        int genericEnd = classInfo.indexOf("]");

        if(genericStart == -1 || genericEnd == -1) {
            return classInfo;
        }

        Generic generic = getDecodedNestedTypeGeneric(generics,classInfo);
        return generic.data;
    }

    private Generic getDecodedNestedTypeGeneric(List<Generic> generics, String classInfo) {
        int genericStart = classInfo.indexOf("[");
        int genericEnd = classInfo.indexOf("]");

        if(genericStart == -1 || genericEnd == -1) {
            return null;
        }

        String key = classInfo.substring(genericStart + 1, genericEnd);
        Generic generic = null;
        for (Generic _generic : generics) {
            if (_generic.key.equals(key)) {
                generic = _generic;
                break;
            }
        }  

        return generic;
    }

    
    private Type resolveTypeByName(TypeVariable[] typeParameters, String typeName) {        
        for(TypeVariable variable : typeParameters) {
            if(variable == null)
                continue;

            if(variable.getName().equals(typeName)) {
                return variable;
            }
        }    

        return editableClass.resolveClassByName(typeName); 
    }

    private Type[] getGeneric(TypeVariable[] declaredGeneric, String info) {
        int genericStart = info.indexOf("[");
        int genericEnd = info.lastIndexOf("]");
        if(genericStart == -1 || genericEnd == -1) {
            return new Type[0];
        }

        List<Generic> generics = new ArrayList<>();

        String val = info.substring(genericStart + 1, genericEnd);    
        val = encodeNestedType(val.replace("[","<")
                               .replace("]",">"),generics);

        String[] genericDeclarations = val.split(",");     

        Type[] typeParameters = new Type[genericDeclarations.length];
        //String[] typesString = new String[genericDeclarations.length];

        for(int i = 0; i < genericDeclarations.length; i++) {
            String genericDeclaration = decodeNestedType(generics,genericDeclarations[i])
                .replace("<","[")
                .replace(">","]");

            boolean superIsAnObject = false;                                    
            String typeWithGenericDeclaration, name, congjungtion = "";
            int startExtends = genericDeclaration.indexOf(" extends ");            
            if(startExtends == -1) {
                int startSuper = genericDeclaration.indexOf(" super ");
                if(startSuper == - 1) {
                    name = genericDeclaration.trim();       
                    typeWithGenericDeclaration = "java.lang.Object";
                } else {
                    name = genericDeclaration.substring(0,startSuper + 1)
                        .trim();
                    typeWithGenericDeclaration = genericDeclaration.substring(startSuper + " super ".length());  
                    superIsAnObject = getClassName(typeWithGenericDeclaration)
                        .equals("Object");
                    congjungtion = " super ";
                }              
            } else {
                name = genericDeclaration.substring(0,startExtends + 1)
                    .trim();
                typeWithGenericDeclaration = genericDeclaration.substring(startExtends + " extends ".length());   
                congjungtion = " extends ";
            }

            Type type;        
            if (name.equals("?")) {
                if(superIsAnObject) {
                    type = editableClass.resolveClassByName("java.lang.Object");
                } else {
                    String typeName = getClassName(typeWithGenericDeclaration);
                    Type[] upperBounds = new Type[]{resolveTypeByName(declaredGeneric, typeName)};
                    Type[] lowerBounds = new Type[0];  

                    String className = editableClass.resolveClassByName(typeName).getName();
                    String wildcardTypeName = name + (className.equals("java.lang.Object") ? "" : congjungtion + className);

                    if(congjungtion.equals(" super ")) {
                        lowerBounds = upperBounds;
                        upperBounds = new Type[]{editableClass.resolveClassByName("java.lang.Object")};
                    }

                    type = new WildcardTypeImpl(wildcardTypeName, upperBounds, lowerBounds);         
                }                  
            } else {
                name = getClassName(name);
                type = resolveTypeByName(declaredGeneric,name);
            } 

            Type[] types = getGeneric(declaredGeneric,genericDeclaration);
            if(types.length != 0) {                           
                type = new ParameterizedTypeImpl(type,types);
            }

            typeParameters[i] = type;
        }

        return typeParameters;
    }
  
}

class Generic {

    Bracket bracket;
    String data;
    String key;

    Generic(Bracket bracket, String data) {
        this.bracket = bracket;
        this.data = data;

        this.key = random(data.length());
    }

    String random(int length) {            
        char[] chars = new char[length];
        for(int i = 0; i < length; i++){
            chars[i] = getChar();
        }
        return String.valueOf(chars);       
    }
    
    char getChar() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Character[] character = new Character[chars.length];

        for(int i = 0; i < chars.length; i++){
            character[i] = chars[i];
        }

        int index = (int)(Math.random() * character.length);
        if(index == character.length)
            index--;

        return character[index];
    }
}


    
