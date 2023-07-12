package test.javaparser.packages;
import java.io.File;
import org.junit.Test;
import com.abiddarris.javaparser.ClassLoader;
import test.javaparser.ClassLoaderSingleton;
import com.abiddarris.javaparser.EditablePackage;

public class PackageTest {
    
    private ClassLoader loader = ClassLoaderSingleton.getInstance();
    
    @Test
    public void getPackages() {
        EditablePackage[] editablePackages = loader.getPackages();
        for(EditablePackage editablePackage : editablePackages) {
            System.out.println(editablePackage.getName());
        }
    }
    
}
