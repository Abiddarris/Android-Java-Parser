package com.abiddarris.javaparser;

public class NoSuchFieldException extends ReflectiveOperationException{
   
    public NoSuchFieldException() {
    }

    public NoSuchFieldException(String s) {
        super(s);
    }

}

