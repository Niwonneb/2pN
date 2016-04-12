package de.htwg.se.tpn.model;


public class Tile implements Cloneable {

    private int value;

    public Tile() {
        value = 2;
    }

    public Tile(int value) {
        this.value = value;
    }

    public void doubleValue() {
        value *= 2;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Tile(value);
    }
}
