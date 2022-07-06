package com.example.springserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "preparatrecept")
public class PreparatRecept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int vipisano;
    public int doza;
    public int kolvodoz;
    public int kolvokurs;
    public String kurs;
//    public String sppr;
//    public String edizm;



//    public int idrec;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idrec")
    private Recept recept;

    @JsonIgnore
    public Recept getRecept() {
        return recept;
    }
//    public int idpre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idpre")
    private Preparat preparat;

    @JsonIgnore
    public Preparat getPreparat() {
        return preparat;
    }

    public PreparatRecept(int id, String sppr
//            , String edizm
            , int vipisano, int doza, int kolvodoz, int kolvokurs, String kurs){
        this.id =id;
//        this.sppr = sppr;
//        this.edizm = edizm;
        this.vipisano = vipisano;
        this.doza = doza;
        this.kolvodoz = kolvodoz;
        this.kolvokurs = kolvokurs;
        this.kurs = kurs;

    }

    public PreparatRecept() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

//    public void setSppr(String sppr) {
//        this.sppr = sppr;
//    }
//    public String getSppr() {
//        return sppr;
//    }

//    public void setEdizm(String edizm) {
//        this.edizm = edizm;
//    }
//    public String getEdizm() {
//        return edizm;
//    }

    public void setVipisano(int vipisano) {
        this.vipisano = vipisano;
    }
    public int getVipisano() {
        return vipisano;
    }

    public void setDoza(int doza) {
        this.doza = doza;
    }
    public int getDoza() {
        return doza;
    }

    public void setKolvodoz(int kolvodoz) {
        this.kolvodoz = kolvodoz;
    }
    public int getKolvodoz() {
        return kolvodoz;
    }

    public void setKolvokurs(int kolvokurs) {
        this.kolvokurs = kolvokurs;
    }
    public int getKolvokurs() {
        return kolvokurs;
    }

    public void setKurs(String kurs) {
        this.kurs = kurs;
    }
    public String getKurs() {
        return kurs;
    }

    @Override
    public String toString(){
        return String.format("ID:%s | F:%s | I:%s | O:%s | ecp:%s",
                this.id,this.vipisano,this.doza,this.kolvodoz,this.kolvokurs,this.kurs
//                ,this.idrec,this.idpre
        );
    }
}
