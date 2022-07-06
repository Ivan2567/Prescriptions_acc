package com.example.springserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "preparat")
public class Preparat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int kolvo;
    public String prepname;
    public String sppr;
    public String edizm;

    @OneToMany(mappedBy = "preparat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreparatRecept> preparatRecepts;

    public Preparat(int id, int kolvo, String prepname, String sppr, String edizm){
        this.id =id;
        this.prepname = prepname;
        this.kolvo = kolvo;
        this.sppr = sppr;
        this.edizm = edizm;

    }

    public Preparat() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setKolvo(int kolvo) {
        this.kolvo = kolvo;
    }
    public int getKolvo() {
        return kolvo;
    }

    public void setPrepname(String prepname) {
        this.prepname = prepname;
    }
    public String getPrepname() {
        return prepname;
    }

    public void setSppr(String sppr) {
        this.sppr = sppr;
    }
    public String getSppr() {
        return sppr;
    }

    public void setEdizm(String edizm) {
        this.edizm = edizm;
    }
    public String getEdizm() {
        return edizm;
    }


    @Override
    public String toString(){
        return String.format("ID:%s | F:%s | I:%s | O:%s | ecp:%s",
                this.id,this.kolvo,this.sppr,this.edizm);
    }
}
