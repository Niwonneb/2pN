package de.htwg.se.tpn.util.persistence.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "saveGame")
public class PersistentSaveGame implements Serializable {

    private static final long serialVersionUID = 1532222903825440126L;

    @Id
    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "saveGame")
    @Column(name = "gameField")
    private PersistentTile[][] gameField;

    public PersistentSaveGame() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersistentTile[][] getGameField() {
        return gameField;
    }

    public void setGameField(PersistentTile[][] gameField) {
        this.gameField = gameField;
    }

    public int getHeight() {
        return gameField.length;
    }
}