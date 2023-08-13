package test.javaparser.annotations;

import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;
import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.Class;

import static test.javaparser.ClassEqualizer.equalsClass;

public class AnnotationsTest {
    
    ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void getDeclaredAnnotation() {
        /*java.lang.Class javaClass = Helper.class;         
        for(java.lang.annotation.Annotation annotation : javaClass.getDeclaredAnnotations()) {
            System.out.println(annotation.annotationType());
            System.out.println((HelperClass)annotation);
            HelperClass s;         
            System.out.println(annotation.getClass().getSuperclass());
        }

        /*Class clazz = loader.loadClass("test.javaparser.fields.FieldClass");
        equalsClass(loader,javaClass, clazz);            */
    }
    
}
