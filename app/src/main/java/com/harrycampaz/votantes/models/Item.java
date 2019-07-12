package com.harrycampaz.votantes.models;

public class Item {

    private String name;
    private int id;

    public Item() {
    }

    public Item(String contact_name, int contact_id) {
        this.name = contact_name;
        this.id = contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Pay attention here, you have to override the toString method as the
     * ArrayAdapter will reads the toString of the given object for the name
     *
     * @return name
     */
    @Override
    public String toString() {
        return name;
    }

}
