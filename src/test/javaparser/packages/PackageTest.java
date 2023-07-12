package test.javaparser.packages;

import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.EditablePackage;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;
import com.abiddarris.javaparser.EditableClass;

public class PackageTest {
    
    private ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void getPackages() {
        EditablePackage[] editablePackages = loader.getPackages();       
    }
    
    @Test
    public void getPackageClasses() {
        EditablePackage[] editablePackages = loader.getPackages();  
        for(EditableClass clazz : editablePackages[5].getClasses()) {
            System.out.println(clazz.getName());
        }
    }
}
