package de.htwg.se.tpn.view;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.Creator;
import de.htwg.se.tpn.controller.AddUIRequest;
import de.htwg.se.tpn.controller.Command;
import de.htwg.se.tpn.controller.RemoveUIRequest;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldInterface;

public class GUIActor extends UIActor {
    private GUI gui;
    private ActorRef controller;

    private GUIActor(GameFieldInterface gameField, ActorRef controller) {
        this.controller = controller;
        controller.tell(new AddUIRequest(getSelf()), getSelf());
        gui = new GUI(gameField, this);
    }

    public static Props props(GameFieldInterface gameField, ActorRef controller) {
        return Props.create(new Creator<GUIActor>() {
            @Override
            public GUIActor create() throws Exception {
                return new GUIActor(gameField, controller);
            }
        });
    }

    public void handleLoadedGameEvent(GameFieldInterface gameField) {
        gui.dispose();
        gui = new GUI(gameField, this);
    }

    @Override
    public void handleNewFieldEvent(GameFieldInterface gameField) {
        gui.handleNewFieldEvent(gameField);
    }

    @Override
    public void handleGameOverEvent(GameFieldInterface gameField) {
        gui.handleGameOverEvent(gameField);
    }

    @Override
    public void handleNewGameEvent(GameFieldInterface gameField) {
        handleLoadedGameEvent(gameField);
    }

    @Override
    public void handleErrorEvent(String message) {
        gui.handleErrorEvent(message);
    }

    public void newGame(int size, int inserts) {
        controller.tell(new Command("new " + size + " " + inserts), getSelf());
    }

    public void saveGame(String name) {
        controller.tell(new Command("save " + name), getSelf());
    }

    public void loadGame(String name) {
        controller.tell(new Command("load " + name), getSelf());
    }

    public void actionUp() {
        controller.tell(new Command("w"), getSelf());
    }

    public void actionDown() {
        controller.tell(new Command("s"), getSelf());
    }

    public void actionLeft() {
        controller.tell(new Command("a"), getSelf());
    }

    public void actionRight() {
        controller.tell(new Command("d"), getSelf());
    }
}
