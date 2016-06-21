package de.htwg.se.tpn.model;

import de.htwg.se.tpn.persistence.hibernate.PersistentSaveGame;
import de.htwg.se.tpn.persistence.hibernate.PersistentTile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergej on 18/04/16.
 */
public interface PersistentSaveGameInterface {
    String getSaveGameId();

    void setSaveGameId(String saveGameId);

    List<PersistentRow> getRows();

    void setRows(List<PersistentRow> gameField);

    int getHeight();

    @Entity
    @Table(name = "tpnRow")
    public static class PersistentRow implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Integer id;

        @OneToMany(mappedBy = "row")
        @Column(name = "tiles")
        public List<PersistentTile> tiles = new LinkedList<>();

        @ManyToOne
        @JoinColumn(name = "saveGameId")
        public PersistentSaveGame saveGame;

        public PersistentSaveGame getSaveGame() {
            return saveGame;
        }

        public void setSaveGame(PersistentSaveGame tpnSaveGame) {
            this.saveGame = tpnSaveGame;
        }
    }
}
