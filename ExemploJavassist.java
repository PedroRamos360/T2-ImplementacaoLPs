import javassist.*;

public class ExemploJavassist {
    public static void main(String[] args) {
        try {
            // Cria uma instância de ClassPool
            ClassPool classPool = ClassPool.getDefault();

            // Cria uma nova classe chamada "Person"
            CtClass personClass = classPool.makeClass("Person");

            // Adiciona um campo "name" à classe
            CtField nameField = new CtField(classPool.get("java.lang.String"), "name", personClass);
            personClass.addField(nameField);

            // Adiciona um construtor padrão à classe
            CtConstructor constructor = new CtConstructor(new CtClass[]{}, personClass);
            constructor.setBody("{ }");
            personClass.addConstructor(constructor);

            // Adiciona um método "setName" à classe
            CtMethod setNameMethod = CtNewMethod.make("public void setName(String name) { this.name = name; }", personClass);
            personClass.addMethod(setNameMethod);

            // Adiciona um método "getName" à classe
            CtMethod getNameMethod = CtNewMethod.make("public String getName() { return name; }", personClass);
            personClass.addMethod(getNameMethod);

            // Salva a classe em um arquivo (opcional)
            personClass.writeFile();

            // Cria uma instância da classe "Person" dinamicamente
            Class<?> personClazz = personClass.toClass();
            Object personInstance = personClazz.newInstance();

            // Acessa e modifica os atributos e métodos da instância dinamicamente
            personClazz.getMethod("setName", String.class).invoke(personInstance, "João");
            String name = (String) personClazz.getMethod("getName").invoke(personInstance);

            System.out.println("Nome: " + name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
