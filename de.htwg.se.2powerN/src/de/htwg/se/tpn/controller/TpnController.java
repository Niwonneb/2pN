package de.htwg.se.tpn.controller;

import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.DirectionInterface;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.util.observer.Observable;

public class TpnController extends Observable implements TpnControllerInterface {
    private GameFieldInterface gamefield;
    private DirectionInterface lastDirection;
    private int inserts;

    public TpnController(int size, int inserts) {
        gameInit(size, inserts);
    }

    public final void gameInit(int size, int inserts) {
        gamefield = new GameField(size);
        this.inserts = inserts;

        for (int i = 0; i < inserts; i++) {
            gamefield.insertRandomNumberTile();
        }
        notifyObservers(new NewGameEvent());
    }

    public void gamereset(int size) {
        gamefield = new GameField(size);
    }

    public int getSize() {
        return gamefield.getHeight();
    }

    public int getValue(int row, int column) {
        return gamefield.getValue(row, column);
    }

    public void insert(int chance, int row, int column) {
        gamefield.insertNumberTile(chance, row, column);
    }

    public enum inputState {
        COMMAND, SAVE, LOAD
    }

    private inputState status = inputState.COMMAND;
    public boolean processInput(String input) {
        switch (status) {
            case LOAD:
                loadGame(input);
                break;
            case SAVE:
                saveGame(input);
                break;
            case COMMAND:
                return executeCommand(input);
            default:
                return false;
        }
        return true;
    }

    private boolean executeCommand(String input) {
        switch (input) {
            case "a":
                actionLeft();
                break;
            case "d":
                actionRight();
                break;
            case "w":
                actionUp();
                break;
            case "s":
                actionDown();
                break;
            case "save":
                status = inputState.SAVE;
                break;
            case "load":
                status = inputState.LOAD;
                break;
            default:
                return false;
        }
        return true;
    }

    private void loadGame(String id) {
        GameFieldInterface loadedGame = new GameField(4);
        gamefield = loadedGame;
        notifyObservers(new NewGameEvent());
    }

    private void saveGame(String id) {

    }

    public boolean actionLeft() {
        return actionDir(new Direction.Left());
    }

    public boolean actionRight() {
        return actionDir(new Direction.Right());
    }

    public boolean actionUp() {
        return actionDir(new Direction.Up());
    }

    public boolean actionDown() {
        return actionDir(new Direction.Down());
    }

    private boolean actionDir(Direction direction) {
        boolean hasMoved;

        hasMoved = gamefield.moveTiles(direction);
        hasMoved = gamefield.mergeTiles(direction) || hasMoved;
        hasMoved = gamefield.moveTiles(direction) || hasMoved;

        if (gamefield.getEmptyPlaces().isEmpty() && !gamefield.trymerge()) {
            notifyObservers(new GameOverEvent());
            return false;
        }
        if (!hasMoved && direction.equals(lastDirection)) {
            return false;
        }
        gamefield.insertRandomNumberTiles(inserts);
        lastDirection = direction;
        notifyObservers(new NewFieldEvent());
        return true;
    }

    public String getId() {
        /**
         * TODO: Give back Session ID if exists, else null
         */
        return "ID";
    }

    public void setGameField(GameFieldInterface gamefield) {
        this.gamefield = gamefield;
    }
}