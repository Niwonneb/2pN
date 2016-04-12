package de.htwg.se.tpn.util.persistence.couchDb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.htwg.se.tpn.model.Tile;

/**
 * Created by Sergej on 12/04/16.
 */
public class PersistenceTile extends Tile {
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
