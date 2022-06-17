package io.github.augusto1217.model;

import java.io.Serializable;

public class Client implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
