package com;

public class Dog {
    private String name;


    public Dog(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public String toString() {
        /*return "Dog{ " +
                "name='" + name + '\'' +
                " }";*/
        return "Dog: " + name;
    }
}
