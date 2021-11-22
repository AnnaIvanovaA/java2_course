package org.database.university.reflect.task.pack3;

import org.database.university.reflect.task.ReflectionClass;

@ReflectionClass
public class CCC {
    String name;
    int number;

    public CCC() {
        this.name = "c class name";
        this.number = 3;
    }

    @Override
    public String toString() {
        return "CCC{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
