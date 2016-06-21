package de.htwg.se.tpn.persistence;

import akka.actor.UntypedActor;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;
import de.htwg.se.tpn.persistence.messages.*;

public abstract class AbstractDao extends UntypedActor implements ITpnDao {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof LoadGameWork) {
            handleLoadGame((LoadGameWork) message);
        } else if (message instanceof SaveGameWork) {
            handleSaveGame((SaveGameWork) message);
        } else if (message instanceof InitWork) {
            handleInit();
        }
    }

    private void handleInit() {
        boolean success = init();
        PersistenceStrategy strategy = getStrategy();
        sender().tell(new InitWorkResult(success, strategy), getSelf());
    }

    private void handleSaveGame(SaveGameWork message) {
        boolean success = createOrUpdateGame(message.gamefield, message.id);
        sender().tell(new SaveGameResult(success, message.id), getSelf());
    }

    private void handleLoadGame(LoadGameWork message) {
        SaveGame saveGame = findGame(message.name);
        GameFieldInterface gamefield = null;
        if (saveGame != null) {
            gamefield = saveGame.getGameField();
        }
        sender().tell(new LoadGameResult(gamefield, message.name), getSelf());
    }
}