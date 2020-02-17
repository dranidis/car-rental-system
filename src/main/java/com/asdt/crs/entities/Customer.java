package com.asdt.crs.entities;

public class Customer implements Cloneable {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Object clone() throws CloneNotSupportedException {
        return (Customer) super.clone();
    }
}
