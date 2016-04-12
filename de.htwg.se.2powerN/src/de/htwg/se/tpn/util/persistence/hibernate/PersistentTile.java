package de.htwg.se.tpn.util.persistence.hibernate;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tile")
public class PersistentTile implements Serializable {

    private static final long serialVersionUID = 3184225396652222648L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public Integer value = 0;

    @ManyToOne
    @JoinColumn(name = "saveGameId")
    public PersistentSaveGame saveGame;


    public PersistentTile(Integer value) {
        this.value = value;
    }

    public PersistentTile() {
    }

    public PersistentSaveGame getSaveGame() {
        return saveGame;
    }

    public void setSaveGame(PersistentSaveGame saveGame) {
        this.saveGame = saveGame;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}