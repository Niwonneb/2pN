package de.htwg.se.tpn.persistence.messages;


public class SaveGameResult {

    public final boolean success;
    public final String name;

    public SaveGameResult(boolean success, String name) {
        this.success = success;
        this.name = name;
    }

    public String toString() {
        return "save game " + name + ". success? " + success;
    }
}
