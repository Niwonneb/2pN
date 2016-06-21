package de.htwg.se.tpn.view;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.Creator;
import de.htwg.se.tpn.controller.*;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldInterface;

import java.util.List;
import java.util.Scanner;

public class TUI extends UIActor {

    private static final int TILESIZE = 7;

    private boolean end;
    private ActorRef controller;


    public static Props props(GameFieldInterface gameField, ActorRef controller) {
        return Props.create(new Creator<TUI>() {
            @Override
            public TUI create() throws Exception {
                return new TUI(gameField, controller);
            }
        });
    }

    public TUI(GameFieldInterface gameField, ActorRef controller) {
        this.controller = controller;
        controller.tell(new AddUIRequest(getSelf()), getSelf());
        printField(gameField);
        end = false;
        readInput();
    }

    private void printField(GameFieldInterface gameField) {
        int height = gameField.getHeight();
        printSeperator(height);
        for (int row = 0; row < height; row++) {
            printNumbers(height, row, gameField);
            printSeperator(height);
        }
    }

    private void printSeperator(int height) {
        for (int i = 0; i < height; i++) {
            print("+");
            for (int j = 0; j < TILESIZE; j++) {
                print("-");
            }
        }
        println("+");
    }

    private void printNumbers(int height, int row, GameFieldInterface gameField) {
        printemptyTileLine(height);
        printTileLine(row, height, gameField);
        printemptyTileLine(height);

    }

    private void printemptyTileLine(int height) {
        for (int collumn = 0; collumn < height; collumn++) {
            printemptyTileSection();
        }
        println("|");
    }

    private void printemptyTileSection() {
        print("|");
        for (int i = 0; i < TILESIZE; i++) {
            print(" ");
        }
    }

    private void printTileLine(int row, int height, GameFieldInterface gameField) {
        int valueLength = 0;

        for (int collumn = 0; collumn < height; collumn++) {

            int value = gameField.getValue(row, collumn);
            if (value == 0) {
                printemptyTileSection();
                continue;
            }

            String strValue = String.valueOf(value);
            valueLength = strValue.length();
            double spaces = (TILESIZE - valueLength);
            int spacesBefore = (int) Math.ceil(spaces / 2);
            int spacesAfter = (int) Math.floor(spaces / 2);

            print("|");
            for (int i = 0; i < spacesBefore; i++) {
                print(" ");
            }

            print(strValue);

            for (int i = 0; i < spacesAfter; i++) {
                print(" ");
            }
        }

        println("|");
    }

    public final void readInput() {
        Scanner inn = new Scanner(System.in);
        while (inn.hasNext()) {
            if (!end) {
                controller.tell(new Command(inn.next()), getSelf());
            }
        }
    }

    private void println(String str) {
        print(str + "\n");
    }

    private void print(String str) {
        System.out.print(str);
    }

    @Override
    public void handleNewFieldEvent(GameFieldInterface gameField) {
        printField(gameField);
    }

    @Override
    public void handleGameOverEvent(GameFieldInterface gameField) {
        if (!end) {
            println("Game Over");
            end = true;
        }
    }

    @Override
    public void handleNewGameEvent(GameFieldInterface gameField) {
        println("New Game");
        end = false;
        printField(gameField);
    }

    @Override
    void handleLoadedGameEvent(GameFieldInterface gameField) {

    }

    @Override
    public void handleErrorEvent(String message) {
        println(message);
    }
}