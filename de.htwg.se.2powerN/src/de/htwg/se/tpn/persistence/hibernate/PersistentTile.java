package de.htwg.se.tpn.persistence.hibernate;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tpnTile")
public class PersistentTile implements Serializable, de.htwg.se.tpn.model.PersistentTileInterface {

    private static final long serialVersionUID = 3184225396652222648L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public Integer value = 0;

    @ManyToOne
    @JoinColumn(name = "rowId")
    public PersistentSaveGame.PersistentRow row;


    public PersistentTile(Integer value) {
        this.value = value;
    }

    public PersistentTile() {
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public PersistentSaveGame.PersistentRow getRow() {
        return row;
    }

    public void setRow(PersistentSaveGame.PersistentRow row) {
        this.row = row;
    }
}