package test.javaparser.classes;

class NonPublicClassHeader {}

public class FirstClass {
    
    public class A {}

    public static class B{}

    protected static class C {}

    static class D {}

    private static class E {
        private static class F {}
    }
    
}
