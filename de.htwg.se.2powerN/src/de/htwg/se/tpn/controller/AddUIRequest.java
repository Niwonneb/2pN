package de.htwg.se.tpn.controller;

import akka.actor.ActorRef;

public class AddUIRequest {
    public ActorRef ui;
    public AddUIRequest(ActorRef ui) {
        this.ui = ui;
    }
}
