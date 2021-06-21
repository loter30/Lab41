package com.example.lab41;

public class Animal {

    private int _id;
    private String specie;
    private String color;
    private float size;
    private String desc;

    public Animal(){}

    public Animal(String specie, String color, float size, String desc) {
        this.specie = specie;
        this.color = color;
        this.size = size;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "_id=" + _id +
                ", specie='" + specie + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public String getSpecie() {
        return specie;
    }

    public String getColor() {
        return color;
    }

    public float getSize() {
        return size;
    }

    public String getDesc() {
        return desc;
    }

    public void set_id(int id){this._id = id};
}

