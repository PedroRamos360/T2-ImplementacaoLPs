import javassist.*;

// Esse exemplo cria uma nova classe chamada Person, adiciona um campo "name",
// um construtor padrão, um método "setName" e um método "getName". Posteriormente
// a classe é salva em um arquivo e uma instância é criada dinamicamente e seus
// atributos e métodos são acessados e modificados dinamicamente.

public class Exemplo2 {
    public static void main(String[] args) {
        try {
            ClassPool classPool = ClassPool.getDefault();

            CtClass personClass = classPool.makeClass("Person");

            CtField nameField = new CtField(classPool.get("java.lang.String"), "name", personClass);
            personClass.addField(nameField);

            CtConstructor constructor = new CtConstructor(new CtClass[]{}, personClass);
            constructor.setBody("{ }");
            personClass.addConstructor(constructor);

            CtMethod setNameMethod = CtNewMethod.make("public void setName(String name) { this.name = name; }", personClass);
            personClass.addMethod(setNameMethod);

            CtMethod getNameMethod = CtNewMethod.make("public String getName() { return name; }", personClass);
            personClass.addMethod(getNameMethod);

            personClass.writeFile();

            Class<?> personClazz = personClass.toClass();
            Object personInstance = personClazz.newInstance();

            personClazz.getMethod("setName", String.class).invoke(personInstance, "João");
            String name = (String) personClazz.getMethod("getName").invoke(personInstance);

            System.out.println("Nome: " + name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
