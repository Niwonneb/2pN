package de.htwg.se.tpn;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import de.htwg.se.tpn.controller.TpnController;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.persistence.couchDb.CouchDbDao;
import de.htwg.se.tpn.persistence.db4o.Db4oDao;
import de.htwg.se.tpn.persistence.hibernate.HibernateDao;
import de.htwg.se.tpn.view.GUIActor;
import de.htwg.se.tpn.view.TUI;

import java.util.LinkedList;
import java.util.List;


public final class TwoPN {
    private static final int FIELDSIZE = 4;
    private static final int INSERTS = 1;

    public ActorSystem actorSystem;
    public List<ActorRef> databases;
    public GameFieldInterface gamefield;
    public ActorRef controller;

    public static void main(String[] args) {
        TwoPN game = new TwoPN();
        game.startTUI();
        game.startGUI();
    }

    public TwoPN() {
        initActors();
    }

    public void initActors() {
        actorSystem = ActorSystem.create("tpn");

        databases = new LinkedList<>();
        databases.add(actorSystem.actorOf(Props.create(Db4oDao.class), "db4o"));
        //databases.add(actorSystem.actorOf(Props.create(CouchDbDao.class), "couch"));
        //databases.add(actorSystem.actorOf(Props.create(HibernateDao.class), "hibernate"));

        gamefield = new GameField(FIELDSIZE);

        controller = actorSystem.actorOf(TpnController.props(gamefield, INSERTS, databases), "controller");
    }

    public void startTUI() {
        actorSystem.actorOf(TUI.props(gamefield, controller), "tui");
    }

    public void startGUI() {
        actorSystem.actorOf(GUIActor.props(gamefield, controller), "gui");
    }
}