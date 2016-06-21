package de.htwg.se.tpn.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import de.htwg.se.tpn.model.*;
import de.htwg.se.tpn.persistence.messages.*;
import de.htwg.se.tpn.persistence.db4o.Db4oDao;
import de.htwg.se.tpn.view.UIEvent;

import java.util.LinkedList;
import java.util.List;

public class TpnController extends UntypedActor implements TpnControllerInterface {
    private GameFieldInterface gamefield;
    private DirectionInterface lastDirection;
    private List<ActorRef> databases;
    private int inserts;

    private ActorRef db;

    public static Props props(GameFieldInterface gameField, int inserts, List<ActorRef> databases) {
        return Props.create(new Creator<TpnController>() {
            @Override
            public TpnController create() throws Exception {
                return new TpnController(gameField, inserts, databases);
            }
        });
    }

    public TpnController(GameFieldInterface gameField, int inserts, List<ActorRef> databases) {
        this.gamefield = gameField;
        this.databases = databases;
        gameInit(gameField, inserts);

        InitWork work = new InitWork();
        databases.get(0).tell(work, getSelf());
    }

    public final void gameInit(GameFieldInterface gamefield, int inserts) {
        this.inserts = inserts;

        for (int i = 0; i < inserts; i++) {
            gamefield.insertRandomTile();
        }
        notifyUIs(new UIEvent(gamefield, UIEvent.Type.NewGame));
    }

    public void gamereset(int size) {
        gamefield.newGrid(size);
    }

    public void setInserts(int inserts) {
        this.inserts = inserts;

        for (int i = 0; i < inserts; i++) {
            gamefield.insertRandomTile();
        }
        notifyUIs(new UIEvent(gamefield, UIEvent.Type.NewGame));
    }

    public int getSize() {
        return 0; //gamefield.getHeight();
    }

    public int getValue(int row, int column) {
        return 0; //gamefield.getValue(row, column);
    }

    public void insert(int chance, int row, int column) {
        //gamefield.insertTile(chance, row, column);
    }

    public enum inputState {
        COMMAND, SAVE, LOAD, NEWSIZE, NEWINSERTS
    }

    private inputState status = inputState.COMMAND;
    public boolean processInput(String input) {
        switch (status) {
            case NEWSIZE:
                int size;
                try {
                    size = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    size = 4;
                }
                gamereset(size);
                status = inputState.NEWINSERTS;
                break;
            case NEWINSERTS:
                int inserts;
                try {
                    inserts = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    inserts = 2;
                }
                setInserts(inserts);
                status = inputState.COMMAND;
                break;
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
            case "new":
                status = inputState.NEWSIZE;
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

    public void saveGame(String id) {
        if (db != null) {
            SaveGameWork work = new SaveGameWork(TpnController.this.gamefield, id);
            db.tell(work, getSelf());
        }
    }

    public void loadGame(String id) {
        if (db != null) {
            LoadGameWork work = new LoadGameWork(id);
            db.tell(work, getSelf());
        }
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
            notifyUIs(new UIEvent(gamefield, UIEvent.Type.GameOver));
            return false;
        }
        if (!hasMoved && direction.equals(lastDirection)) {
            return false;
        }
        gamefield.insertRandomTiles(inserts);
        lastDirection = direction;
        notifyUIs(new UIEvent(gamefield, UIEvent.Type.NewField));
        return true;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Command) {
            handleCammand((Command) message);
        } else if (message instanceof AddUIRequest) {
            addUI((AddUIRequest) message);
        } else if (message instanceof RemoveUIRequest) {
            removeUI((RemoveUIRequest) message);
        } else if (message instanceof InitWorkResult) {
            handleInitWorkResult((InitWorkResult)message);
        } else if (message instanceof LoadGameResult) {
            handleLoadGameResult((LoadGameResult) message);
        } else if (message instanceof SaveGameResult) {
            handleSaveGameResult((SaveGameResult) message);
        }
    }

    private void handleCammand(Command command) {
        String[] commands = command.text.split(" ");
        for (String commandString : commands) {
            if (!processInput(commandString)) {
                notifyUIs(new UIEvent(null, UIEvent.Type.Error, "Please type w, a, s, d to play or save/load/new"));
            }
        }
    }

    private void handleInitWorkResult(InitWorkResult message) {
        if (message.success) {
            db = getSender();
        } else {
            System.out.println("could not initialize database: " + message.strategy.name());
        }
    }

    private void handleSaveGameResult(SaveGameResult message) {
        if (!message.success) {
            notifyUIs(new UIEvent(null, UIEvent.Type.Error, "Could not save Game"));
        }
    }

    private void handleLoadGameResult(LoadGameResult message) {
        if (message.gamefield != null) {
            gamefield =  message.gamefield;
            notifyUIs(new UIEvent(gamefield, UIEvent.Type.NewGame));
        } else {
            notifyUIs(new UIEvent(null, UIEvent.Type.Error, "Could not load Game"));
        }
    }

    private List<ActorRef> UIs = new LinkedList<>();

    private void addUI(AddUIRequest request) {
        UIs.add(request.ui);
    }

    private void removeUI(RemoveUIRequest request) {
        UIs.remove(request.ui);
    }

    private void notifyUIs(Object message) {
        for (ActorRef ui : UIs) {
            ui.tell(message, getSelf());
        }
    }
}