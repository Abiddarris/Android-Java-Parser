package com.abiddarris.javaparser;

import com.abiddarris.javaparser.implementations.ArrayClass;
import com.abiddarris.javaparser.implementations.EditableClass;
import com.abiddarris.javaparser.implementations.EditableClassImpl;
import com.abiddarris.javaparser.implementations.EditablePackage;
import com.abiddarris.javaparser.implementations.JavaFile;
import com.abiddarris.javaparser.java.Modifier;
import com.abiddarris.javaparser.wrappers.ClassWrapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileClassLoader extends ClassLoader {
    
    private File root;
    private EditablePackage[] packages;

    public FileClassLoader(ClassLoader parent, File root) {
        super(parent);
        
        this.root = root;
    }

    @Override
    public Class loadClass(String name) {
        try {
            return loadEditableClass(name);
        } catch (Exception e) {           
        }

        try {
            return loadClassWrapper(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClassWrapper loadClassWrapper(String name) throws ClassNotFoundException {
        return getParent().loadClassWrapper(name);
    }

    @Override
    public EditableClass loadEditableClass(String name) {
        if(name.startsWith("[") && name.endsWith(";")) {
            return loadArrayClass(name);
        }
        
        String innerClassPath = null;
        int dollarSign = name.indexOf("$");
        if(dollarSign != -1) {
            innerClassPath = name.substring(dollarSign + 1);
            name = name.substring(0,dollarSign);
        }

        int period = name.lastIndexOf(".");
        String packageName = "";
        if(period != -1) {
            packageName = name.substring(0,period);
            name = name.substring(period + 1);
        }

        EditablePackage editablePackage = getPackage(packageName);
        JavaFile javaFile = editablePackage.getJavaFile(name);
        EditableClassImpl clazz = null;
        if(javaFile == null) {
            EditableClassImpl[] classes = editablePackage.getClasses();
            for(EditableClassImpl editableClass : classes) {
                if(editableClass.getSimpleName().equals(name)) {
                    clazz = editableClass;
                    break;
                }
            }
            if(clazz == null) throw new NullPointerException();
        } else {
            EditableClassImpl[] classes = javaFile.getClasses();
            for(EditableClassImpl _clazz : classes) {
                if(Modifier.isPublic(_clazz.getModifiers())) {
                    clazz = _clazz;
                    break;
                }
            }

            if(clazz == null) clazz = classes[0];

        }

        if(innerClassPath != null) {
            return (EditableClassImpl) clazz.getInnerClass(innerClassPath);
        }

        return clazz;    
    }

    private EditableClass loadArrayClass(String name) {      
        name = name.substring(1);
        EditableClass component = null;
        if(name.startsWith("L")) {
            name = name.substring(1,name.length() - 1);
            component = loadEditableClass(name);            
        } else if(name.startsWith("[")) {
            component = loadArrayClass(name);
        }
        
        ArrayClass a = new ArrayClass(component);
        return a;
    }

    @Override
    public EditablePackage[] getPackages() {
        if(packages == null) {
            List<EditablePackage> packages = toArrayList(getParent().getPackages());
            
            List<EditablePackage> editablePackages = new ArrayList<>();
            createPackages(editablePackages,root);   
            combinePackages(editablePackages, packages);
            
            this.packages = editablePackages.toArray(new EditablePackage[0]);
        }
        return packages;
    }

    private void combinePackages(List<EditablePackage> thisPackages, List<EditablePackage> packages) {
        for(int i = 0; i < thisPackages.size(); i++) {
            EditablePackage thisPackage = thisPackages.get(i);         
            for(EditablePackage parentPackage : packages) {
                if (thisPackage.getName().equals(parentPackage.getName())) {
                    JavaFile[] javaFiles = combineJavaFiles(parentPackage.getJavaFiles(), thisPackage.getJavaFiles());
                    parentPackage.setClassPaths(javaFiles);    
                    
                    thisPackages.remove(i);
                    thisPackages.add(i,parentPackage);
                    
                    packages.remove(parentPackage);
                    break;
                }
            }            
        }
        
        for(EditablePackage parentPackage : packages) {
            thisPackages.add(parentPackage);
        }
    }

    private JavaFile[] combineJavaFiles(JavaFile[] oldJavaFiles, JavaFile[] newJavaFiles) {
        List<JavaFile> javaFiles = toArrayList(oldJavaFiles);
        for(JavaFile newJavaFile : newJavaFiles) {
            boolean exists = false;
            for(JavaFile oldJavaFile : javaFiles) {
                if(newJavaFile.getName().equals(oldJavaFile.getName())) {
                    exists = true;
                    break;
                }
            }
            if(!exists) {
                javaFiles.add(newJavaFile);
            }
        }
        return javaFiles.toArray(new JavaFile[0]);
    }

    private <T> List<T> toArrayList(T[] array) {
        List<T> list = new ArrayList<>();
        for(T element : array) {
            list.add(element);
        }
        return list;
    }
    
    private void createPackages(List<EditablePackage> editablePackages, File packageFile) {
        String name = packageFile.getPath()
            .replace(root.getPath(),"")
            .replace("/",".");
        if(name.startsWith(".")) name = name.substring(1);

        List<JavaFile> classPaths = new ArrayList<>();

        EditablePackage editablePackage = new EditablePackage(name);
        editablePackages.add(editablePackage);

        File[] files = packageFile.listFiles();         
        for(File file : files) {
            if(file.isDirectory()) {
                createPackages(editablePackages,file);
            } else {               
                classPaths.add(new JavaFile(this,file.getPath()));
            }
        }
        editablePackage.setClassPaths(classPaths.toArray(new JavaFile[0]));
    }
    
    
}
