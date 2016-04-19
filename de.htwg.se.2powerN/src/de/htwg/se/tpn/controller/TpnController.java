package de.htwg.se.tpn.controller;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.htwg.se.tpn.model.*;
import de.htwg.se.tpn.util.observer.Observable;
import de.htwg.se.tpn.util.persistence.ITpnDao;
import de.htwg.se.tpn.util.persistence.SaveAndLoadService;

public class TpnController extends Observable implements TpnControllerInterface {
    private GameFieldInterface gamefield;
    private DirectionInterface lastDirection;
    private SaveAndLoadService saveAndLoadService;
    private int inserts;

@Inject
public TpnController(@Named("size") int size, @Named("inserts") int inserts, ITpnDao dao) {
        saveAndLoadService = new SaveAndLoadService(dao);
        gameInit(size, inserts);
    }

    public final void gameInit(int size, int inserts) {
        gamefield = new GameField(size);
        this.inserts = inserts;

        for (int i = 0; i < inserts; i++) {
            gamefield.insertRandomTile();
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
        gamefield.insertTile(chance, row, column);
    }

    public enum inputState {
        COMMAND, SAVE, LOAD
    }

    private inputState status = inputState.COMMAND;
    public boolean processInput(String input) {
        switch (status) {
            case LOAD:
                loadGame(input);
                status = inputState.COMMAND;
                break;
            case SAVE:
                saveGame(input);
                status = inputState.COMMAND;
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

    public void loadGame(String id) {
        SaveGame loadedGame = saveAndLoadService.loadGame(id);
        if (loadedGame != null) {
            gamefield = loadedGame.getGameField();
            notifyObservers(new GameLoadedEvent());
            notifyObservers(new NewFieldEvent());
        }
    }

    public boolean saveGame(String id) {
        return saveAndLoadService.saveGame(gamefield, id);
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
        gamefield.insertRandomTiles(inserts);
        lastDirection = direction;
        notifyObservers(new NewFieldEvent());
        return true;
    }
}