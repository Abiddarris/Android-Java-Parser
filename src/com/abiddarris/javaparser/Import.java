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

import java.util.regex.Pattern;

public class Import {
    
    private String _package = "";
    private String name = "";

    public Import(String _package, String name){
        this._package = _package;
        this.name = name;
    }
    
    Import(String code) {       
        String[] subs = code.split(Pattern.quote("."));
        name = subs[subs.length - 1];
        for(int i = subs.length - 2; i >= 0; i--) {
            _package = subs[i] + "." + _package;
        }
        if(_package.endsWith("."))
            _package = _package.substring(0,_package.length() - 1);
    }

    public void setPackage(String _package){
        this._package = _package;
    }

    public String getPackage() {
        return _package;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "import " + _package + "." + name;
    }
    
    
    
}
