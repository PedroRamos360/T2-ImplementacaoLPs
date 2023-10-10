import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;


// Esse exemplo criar uma classe dinâmica chamada DynamicClass, adiciona
// um método dinâmico chamado dynamicMethod e invoca o método dinâmico

public class Exemplo1 {
    public static void main(String[] args) {
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass dynamicClass = classPool.makeClass("DynamicClass");

            CtMethod dynamicMethod = CtNewMethod.make(
                    "public void dynamicMethod() { System.out.println(\"Dynamic method called!\"); }",
                    dynamicClass);
            dynamicClass.addMethod(dynamicMethod);

            Class<?> clazz = dynamicClass.toClass();
            Object instance = clazz.newInstance();
            clazz.getMethod("dynamicMethod").invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
