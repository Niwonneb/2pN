package de.htwg.se.tpn.model;

/**
 * Created by Sergej on 23/03/16.
 */
public class SaveGame {

    public SaveGame(GameFieldInterface gameField, String id) {
        this.gameField = gameField;
        this.id = id;
    }

    private GameFieldInterface gameField;
    private String id;

    public GameFieldInterface getGameField() {
        return gameField;
    }

    public void setGameField(GameFieldInterface gameField) {
        this.gameField = gameField;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
