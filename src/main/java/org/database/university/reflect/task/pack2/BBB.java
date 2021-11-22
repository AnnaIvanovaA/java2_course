package org.database.university.reflect.task.pack2;

public class BBB {
    String name;
    int number;

    public BBB() {
        this.name = "b class name";
        this.number = 2;
    }


    @Override
    public String toString() {
        return "BBB{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
