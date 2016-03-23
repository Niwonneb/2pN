package de.htwg.se.tpn.model;

public interface Tile extends Cloneable {
    int getValue();

    void doubleValue();

    Object clone() throws CloneNotSupportedException;
}
