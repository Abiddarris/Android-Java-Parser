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

import com.abiddarris.javaparser.ClassLoader;
import java.io.File;
import com.abiddarris.javaparser.FileClassLoader;

public class ClassLoaderSingleton {
    
    private static ClassLoader loader; 
    
    public static ClassLoader getInstance() {
        if(loader == null) {
            loader = new FileClassLoader(
                ClassLoader.getSystemClassLoader(),
                new File("/storage/emulated/0/AppProjects/Android-Java-Parser/src"));         
        }
        return loader;
    }
}
