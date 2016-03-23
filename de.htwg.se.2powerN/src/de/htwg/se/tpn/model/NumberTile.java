package de.htwg.se.tpn.model;


public class NumberTile implements Tile {

    private int value;

    public NumberTile() {
        value = 2;
    }

    public NumberTile(int value) {
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
        return new NumberTile(value);
    }
}
