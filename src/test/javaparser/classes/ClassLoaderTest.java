package test.javaparser.classes;

import com.abiddarris.javaparser.Boolean;
import com.abiddarris.javaparser.Byte;
import com.abiddarris.javaparser.Character;
import com.abiddarris.javaparser.Class;
import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.Double;
import com.abiddarris.javaparser.FileClassLoader;
import com.abiddarris.javaparser.Float;
import com.abiddarris.javaparser.Integer;
import com.abiddarris.javaparser.Long;
import com.abiddarris.javaparser.Package;
import com.abiddarris.javaparser.Short;
import com.abiddarris.javaparser.implementations.EditableClass;
import java.io.File;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;
import test.javaparser.generics.ClassFile;

import static org.junit.Assert.*;
import static test.javaparser.ClassEqualizer.equalsClass;

public class ClassLoaderTest {
    
    ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void nestedClassLoader() {
        ClassLoader parent = new FileClassLoader(ClassLoader.getSystemClassLoader(),
            new File("/storage/emulated/0/AppProjects/Android-Java-Parser/src"));
        ClassLoader loader = new FileClassLoader(parent,
            new File("/storage/emulated/0/User Data[Abiddarris]/Programming/apis/AbiddarrisStatusWhatsappApi/src"));
        for(Package package0 : loader.getPackages()) {
           // System.out.println(package0.getName());
        }
        Class main = loader.loadClass("Main");
        //System.out.println(main.getDeclaredFields()[0].getName());
    }
    
    @Test
    public void wrapperLoadSameInstance() throws ClassNotFoundException {
        Class a = loader.loadClassWrapper("test.javaparser.classes.ClassLoaderTest");
        Class b = loader.loadClassWrapper("test.javaparser.classes.ClassLoaderTest");
        assertTrue(a == b);
    }
    
    @Test
    public void loadPrimitiveClasses() throws ClassNotFoundException {
        equalsClass(null, boolean.class, Boolean.TYPE);
        equalsClass(null, byte.class, Byte.TYPE);
        equalsClass(null, char.class, Character.TYPE);
        equalsClass(null, double.class, Double.TYPE);
        equalsClass(null, float.class, Float.TYPE);
        equalsClass(null, int.class, Integer.TYPE);
        equalsClass(null, long.class, Long.TYPE);
        equalsClass(null, short.class, Short.TYPE);
    }
    
    @Test
    public void loadTwoDimensionalArrays() throws ClassNotFoundException {      
        EditableClass editableClass = loader.loadEditableClass("[[Ltest.javaparser.generics.ClassFile;");
        equalsClass(loader, ClassFile[][].class, editableClass);
    }
    
    @Test
    public void loadNonEditableClassArray() {
       Class clazz = loader.loadClass("[Ljava.lang.String;");
        equalsClass(loader, String[].class, clazz);
    }
    
    class a<T extends Object> {}
}
