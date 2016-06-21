package de.htwg.se.tpn.persistence.messages;


import de.htwg.se.tpn.persistence.PersistenceStrategy;

public class InitWorkResult {

    public final PersistenceStrategy strategy;
    public final boolean success;
    public InitWorkResult(boolean success, PersistenceStrategy strategy) {
        this.success = success;
        this.strategy = strategy;
    }

    public String toString() {
        return "Init work: " + success;
    }
}
