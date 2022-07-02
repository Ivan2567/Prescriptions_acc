package com.example.entities;

public class Pacient {
    public int id;
    public String f;
    public String i;
    public String o;
    public String polis;
    public Pacient(int id, String f, String i, String o, String polis){
        this.id =id;
        this.f = f;
        this.i = i;
        this.o = o;
        this.polis = polis;
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
