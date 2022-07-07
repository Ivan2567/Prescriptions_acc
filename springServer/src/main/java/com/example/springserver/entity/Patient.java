package com.example.springserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String f;

    public String i;
    public String o;

    public String polis;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recept> recepts;

    public Patient(int id, String f, String i, String o, String polis){
        this.id =id;
        this.f = f;
        this.i = i;
        this.o = o;
        this.polis = polis;
    }

    public Patient() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setF(String f) {
        this.f = f;
    }
    public String getF() {
        return f;
    }

    public void setI(String i) {
        this.i = i;
    }
    public String getI() {
        return i;
    }

    public void setO(String o) {
        this.o = o;
    }
    public String getO() {
        return o;
    }

    public void setPolis(String polis) {
        this.polis = polis;
    }
    public String getPolis() {
        return polis;
    }

    @Override
    public String toString(){
        return String.format("ID:%s | F:%s | I:%s | O:%s | polis:%s",
                this.id,this.f,this.i,this.o,this.polis);
    }
}
