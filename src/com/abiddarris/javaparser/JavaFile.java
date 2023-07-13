package com.abiddarris.javaparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaFile extends File{
    
    private ClassLoader loader;
    private EditableClass[] classes;
    
    public JavaFile(ClassLoader loader, String pathname) {
        super(pathname);
        
        this.loader = loader;
    }

    @Override
    public String getName() {
        return super.getName().replace(".java","");
    }

    
    public void load() {
        StringBuilder builder;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this));
            builder = new StringBuilder();
            String data;
            while((data = reader.readLine()) != null) {
                builder.append(data)
                    .append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        Bracket parent = Bracket.parse(builder.toString(),"{","}");       
        List<Import> imports = handleImports(builder.substring(0,parent.children.get(0).start));

        classes = new EditableClass[parent.children.size()];       
        for(int i = 0; i < classes.length; i++) {
            Bracket bracket = parent.children.get(i);
            EditableClass clazz = new EditableClass(null,imports,loader,builder.toString(),bracket);
            classes[i] = clazz;
        }     
    }
    
    public EditableClass[] getClasses() {
        if(classes == null) {
            load();
        }
        return classes;
    }
    
    private List<Import> handleImports(String importStr) {
        List<Import> imports = new ArrayList<>();
        int importStart = 0;
        while ((importStart = importStr.indexOf("import ", importStart)) != - 1) {
            int semicolon = importStr.indexOf(";", importStart);
            importStart += "import ".length();

            Import _import = new Import(importStr.substring(importStart, semicolon));
            imports.add(_import);
        }
        return imports;
    }
       
}
