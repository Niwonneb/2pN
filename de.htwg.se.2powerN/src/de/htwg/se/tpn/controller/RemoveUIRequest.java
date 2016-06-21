package de.htwg.se.tpn.controller;

import akka.actor.ActorRef;

public class RemoveUIRequest {
    public ActorRef ui;
    public RemoveUIRequest(ActorRef ui) {
        this.ui = ui;
    }
}
