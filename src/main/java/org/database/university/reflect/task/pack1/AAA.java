package org.database.university.reflect.task.pack1;

import org.database.university.reflect.task.ReflectionClass;

@ReflectionClass
public class AAA {
    String name;
    int number;

    public AAA() {
        this.name = "a class name";
        this.number = 1;
    }

    @Override
    public String toString() {
        return "AAA{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }

    @ReflectionClass
    public class NestedClass{

    }
}
