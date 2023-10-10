import javassist.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a new class
            ClassPool classPool = ClassPool.getDefault();
            CtClass dynamicClass = classPool.makeClass("DynamicClass");

            // Add a method to the class
            CtMethod dynamicMethod = CtNewMethod.make(
                    "public void dynamicMethod() { System.out.println(\"Dynamic method called!\"); }",
                    dynamicClass);
            dynamicClass.addMethod(dynamicMethod);

            // Create an instance of the dynamic class and call the dynamic method
            Class<?> clazz = dynamicClass.toClass();
            Object instance = clazz.newInstance();
            clazz.getMethod("dynamicMethod").invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
