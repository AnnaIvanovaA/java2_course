package org.database.university.reflection;

public class Subject {
    private String name;

    private Subject(){}

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, int hours){
        this.name=name;
    }
}
