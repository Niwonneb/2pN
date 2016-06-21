package de.htwg.se.tpn.persistence.couchDb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.htwg.se.tpn.model.GameField;

/**
 * Created by Sergej on 12/04/16.
 */
public class PersistentGame extends GameField {
    private int value;

    @JsonProperty("value")
    public int getValue() {
        return value;
    }

    @JsonProperty("value")
    public void doubleValue() {
        value *= 2;
    }
}
