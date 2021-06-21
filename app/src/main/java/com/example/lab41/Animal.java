package com.example.lab41;

public class Animal {

    private int _id;
    private String gatunek;
    private String kolor;
    private float wielkosc;
    private String opis;

    public Animal(){}

    public Animal(String gatunek, String kolor, float wielkosc, String opis) {
        this.gatunek = gatunek;
        this.kolor = kolor;
        this.wielkosc = wielkosc;
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "_id=" + _id +
                ", gatunek='" + gatunek + '\'' +
                ", kolor='" + kolor + '\'' +
                ", wielkosc=" + wielkosc +
                ", opis='" + opis + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public String getGatunek() {
        return gatunek;
    }

    public String getKolor() {
        return kolor;
    }

    public float getWielkosc() {
        return wielkosc;
    }

    public String getOpis() {
        return opis;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}

