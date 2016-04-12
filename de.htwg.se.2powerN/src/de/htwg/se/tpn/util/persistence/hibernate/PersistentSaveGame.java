package de.htwg.se.tpn.util.persistence.hibernate;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tpnSaveGame")
public class PersistentSaveGame implements Serializable {

    private static final long serialVersionUID = 1532222903825440126L;

    @Id
    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "saveGame")
    @Column(name = "rows")
    private List<PersistentRow> gameField;

    public PersistentSaveGame() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PersistentRow> getRows() {
        return gameField;
    }

    public void setRows(List<PersistentRow> gameField) {
        this.gameField = gameField;
    }

    public int getHeight() {
        return gameField.size();
    }

    @Entity
    @Table(name = "tpnRow")
    public static class PersistentRow implements  Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Integer id;

        @OneToMany(mappedBy = "row")
        @Column(name = "tiles")
        public List<PersistentTile> tiles = new LinkedList<>();

        @ManyToOne
        @JoinColumn(name = "saveGameId")
        public PersistentSaveGame saveGame;
    }
}