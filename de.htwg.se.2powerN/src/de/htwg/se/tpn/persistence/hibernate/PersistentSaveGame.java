package de.htwg.se.tpn.persistence.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tpnSaveGame")
public class PersistentSaveGame implements Serializable, de.htwg.se.tpn.model.PersistentSaveGameInterface {

    private static final long serialVersionUID = 1532222903825440126L;

    @Id
    @Column(name = "id")
    private String saveGameId;

    @OneToMany(mappedBy = "saveGame")
    @Column(name = "rows")
    private List<PersistentRow> gameField;

    public PersistentSaveGame() {
    }

    public String getSaveGameId() {
        return saveGameId;
    }

    public void setSaveGameId(String saveGameId) {
        this.saveGameId = saveGameId;
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