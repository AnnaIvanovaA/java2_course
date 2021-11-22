package org.database.university.reflect.task;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AnnotationPackagesProcessor {

    public static String[] processAnnotation(String packageName){
        //TODO filter interfaces


        //Load the classLoader which loads this class.
        ClassLoader classLoader = AnnotationPackagesProcessor.class.getClassLoader();

        //Change the package structure to directory structure
        String packagePath  = packageName.replace('.', '/');
        URL urls = classLoader.getResource(packagePath);

        //Get all the class files in the specified URL Path.
        File folder = new File(urls.getPath());
        File[] classes = folder.listFiles();

        int size = classes.length;
        List<Class<?>> classList = new ArrayList<Class<?>>();

        try {
            for (int i = 0; i < size; i++) {
                int index = classes[i].getName().indexOf(".");
                String className = classes[i].getName().substring(0, index);
                String classNamePath = packageName + "." + className;
                Class<?> repoClass;
                repoClass = Class.forName(classNamePath);
                Annotation[] annotations = repoClass.getAnnotations();
                for (int j = 0; j < annotations.length; j++) {
                    System.out.println("Annotation in class " + repoClass.getName() + " is " + annotations[j].annotationType().getName());
                }
                classList.add(repoClass);
            }
        } catch (ClassNotFoundException exc){
            System.out.println("Class wasn't found");
        }

        return null;

    }
}
