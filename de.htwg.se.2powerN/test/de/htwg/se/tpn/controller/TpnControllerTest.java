package de.htwg.se.tpn.controller;

import static org.junit.Assert.*;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.persistence.db4o.Db4oDao;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TpnControllerTest {
	ActorRef c;
	public ActorSystem actorSystem;
	public List<ActorRef> databases;
	public GameFieldInterface g;
	public ActorRef controller;

	@Before
	public void setUp() throws Exception {
		actorSystem = ActorSystem.create("tpn");

		databases = new LinkedList<>();
		databases.add(actorSystem.actorOf(Props.create(Db4oDao.class), "db4o"));
		//databases.add(actorSystem.actorOf(Props.create(CouchDbDao.class), "couch"));
		//databases.add(actorSystem.actorOf(Props.create(HibernateDao.class), "hibernate"));

		g = new GameField(2);

		c = actorSystem.actorOf(TpnController.props(g, 0, databases), "controller");
	}

	@Test
	public void testactionLeft() {
		g.insertTile(0, 0, 1);
		assertEquals(true, c.actionLeft());
		g.insertTile(100, 0, 1);
		g.insertTile(0, 1, 0);
		g.insertTile(100, 1, 1);
		assertEquals(false, c.actionLeft());
		g.insertTile(0, 0, 0);
		g.insertTile(100, 0, 1);
		g.insertTile(100, 1, 0);
		g.insertTile(0, 1, 1);
		c.actionLeft();
	}

	@Test
	public void testactionRight() {
		g.insertTile(0, 1, 0);
		assertEquals(true, c.actionRight());
		g.insertTile(0, 0, 1);
		g.insertTile(100, 1, 0);
		g.insertTile(100, 0, 0);
		assertEquals(false, c.actionRight());
		g.insertTile(0, 0, 0);
		g.insertTile(100, 0, 1);
		g.insertTile(100, 1, 0);
		g.insertTile(0, 1, 1);
		c.actionRight();
	}

	@Test
	public void testactionUp() {
		g.insertTile(0, 1, 0);
		assertEquals(true, c.actionUp());
		g.insertTile(0, 0, 1);
		g.insertTile(100, 1, 0);
		g.insertTile(100, 1, 1);
		assertEquals(false, c.actionUp());
		g.insertTile(0, 0, 0);
		g.insertTile(100, 0, 1);
		g.insertTile(100, 1, 0);
		g.insertTile(0, 1, 1);
		c.actionUp();
	}

	@Test
	public void testactionDown() {
		g.insertTile(0, 0, 0);
		assertEquals(true, c.actionDown());
		g.insertTile(100, 0, 1);
		g.insertTile(100, 0, 0);
		g.insertTile(0, 1, 1);
		assertEquals(false, c.actionDown());
		g.insertTile(0, 0, 0);
		g.insertTile(100, 0, 1);
		g.insertTile(100, 1, 0);
		g.insertTile(0, 1, 1);
		c.actionDown();
	}
	
	@Test
	public void testgetValue() {
		assertEquals(0, g.getValue(0, 0));
	}
	
	@Test
	public void testgetSize() {
		assertEquals(2, g.getHeight());
	}
}
