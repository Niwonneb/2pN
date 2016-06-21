package de.htwg.se.tpn.view;

import de.htwg.se.tpn.model.GameFieldInterface;

public class UIEvent {
    public GameFieldInterface gameField;
    public String message;
    public Type type;

    public UIEvent(GameFieldInterface gameField, Type type) {
        this(gameField, type, "");
    }

    public UIEvent(GameFieldInterface gameField, Type type, String message) {
        this.gameField = gameField;
        this.message = message;
        this.type = type;
    }

    public enum Type {GameOver, NewField, NewGame, GameLoaded, Error}
}
