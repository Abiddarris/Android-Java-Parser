package test.javaparser.packages;

import com.abiddarris.javaparser.ClassLoader;
import com.abiddarris.javaparser.implementations.EditableClass;
import com.abiddarris.javaparser.implementations.EditablePackage;
import org.junit.Test;
import test.javaparser.ClassLoaderSingleton;

public class PackageTest {
    
    private ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void getPackages() {
        EditablePackage[] editablePackages = loader.getPackages();       
    }
    
    @Test
    public void getPackageClasses() {
        EditablePackage editablePackage = loader.getPackage("test.javaparser");
        for(EditableClass clazz : editablePackage.getClasses()) {
            //System.out.println(clazz.getName());
        }
    }
}
