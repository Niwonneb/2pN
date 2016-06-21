package de.htwg.se.tpn.view;

import akka.actor.UntypedActor;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldInterface;

public abstract class UIActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (!(message instanceof UIEvent)) {
            return;
        }
        UIEvent event = (UIEvent) message;
        if (event.type == UIEvent.Type.NewField) {
            handleNewFieldEvent(event.gameField);
        } else if (event.type == UIEvent.Type.GameOver) {
            handleGameOverEvent(event.gameField);
        } else if (event.type == UIEvent.Type.NewGame) {
            handleNewGameEvent(event.gameField);
        } else if (event.type == UIEvent.Type.GameLoaded) {
            handleLoadedGameEvent(event.gameField);
        } else if (event.type == UIEvent.Type.Error) {
            handleErrorEvent(event.message);
        }
    }

    abstract void handleNewFieldEvent(GameFieldInterface gameField);
    abstract void handleGameOverEvent(GameFieldInterface gameField);
    abstract void handleNewGameEvent(GameFieldInterface gameField);
    abstract void handleLoadedGameEvent(GameFieldInterface gameField);
    abstract void handleErrorEvent(String gameField);
}
