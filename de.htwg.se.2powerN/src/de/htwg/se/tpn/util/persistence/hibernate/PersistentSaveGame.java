package de.htwg.se.tpn.util.persistence.hibernate;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tpnSaveGame")
public class PersistentSaveGame implements Serializable, de.htwg.se.tpn.model.PersistentSaveGameInterface {

    private static final long serialVersionUID = 1532222903825440126L;

    @Id
    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "saveGame")
    @Column(name = "rows")
    private List<PersistentRow> gameField;

    public PersistentSaveGame() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public List<PersistentRow> getRows() {
        return gameField;
    }

    @Override
    public void setRows(List<PersistentRow> gameField) {
        this.gameField = gameField;
    }

    @Override
    public int getHeight() {
        return gameField.size();
    }

}