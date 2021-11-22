package org.database.university.reflect.task;

public class ObjectsCreationApplication {

    public static void main(String[] args) {

        String[] classesWithAnnotation = AnnotationPackagesProcessor.processAnnotation("org.database.university.reflect.task.pack1");
        System.out.println();
    }
}
