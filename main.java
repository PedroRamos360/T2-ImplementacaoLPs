// Coisas do gpt
// documentação java assist: https://www.javassist.org/


// Javassist example
ClassPool pool = ClassPool.getDefault();
CtClass newClass = pool.makeClass("MyNewClass");

// Javassist example
CtField newField = new CtField(CtClass.intType, "myField", newClass);
newClass.addField(newField);


// Javassist example
CtMethod newMethod = CtNewMethod.make("public void myMethod() { /* method body */ }", newClass);
newClass.addMethod(newMethod);
