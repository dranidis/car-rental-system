package com.asdt.crs.entities;

public class Category implements Cloneable {

    private String id;

    public Category(String string) {
        id = string;
    }

    public String getId() {
        return id;
    }

    public Object clone() throws CloneNotSupportedException {
        return (Category) super.clone();
    }
}
