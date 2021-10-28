package org.database.university.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class JavaReflectionExamples {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //ClassLoader.loadClass(Subject) -> Class<Subject>
        Subject subject = new Subject("Prog");
        Object obj = subject;
        Class<?> objClass = obj.getClass(); //-> Class<Subject>

        //1. object
        Class<?> subjClass = subject.getClass(); //-> Class<Subject>

        //2. Class name and literal .class
        Class<?> subjectClass = Subject.class;

        Field[] fields = subjectClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType().getName() + " " + field.getName());

        }
        System.out.println("Constructors...");
        Constructor<?>[] constructors = subjClass.getDeclaredConstructors();
        for (Constructor<?> constructor: constructors){
            //Subject() -> []
            //Subject(String name) -> [Class<String>]
            Class<?>[] types = constructor.getParameterTypes();
            System.out.println(Arrays.toString(types));
        }

        //subject(...)
        Field subjectNameField = subjectClass.getDeclaredField("name");
        subjectNameField.setAccessible(true);
        String subjectName = (String) subjectNameField.get(subject);
        System.out.println(subjectName);
        subjectNameField.set(subject, "Programming");
        System.out.println((String) subjectNameField.get(subject));
    }
}
