package com.example.springserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "preparatrecept")
public class PreparatRecept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //todo vipisano и подобное поменяй на английские аналоги
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "prescribed", nullable = false)
    private int prescribed;
    @Column(name = "dose", nullable = false)
    private int dose;
    @Column(name = "qtydose", nullable = false)
    private int qtydose;
    @Column(name = "qtykurs", nullable = false)
    private int qtykurs;
    @Column(name = "kurs", nullable = false)
    private String kurs;
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
            , int prescribed, int dose, int qtydose, int qtykurs, String kurs){
        this.id =id;
//        this.sppr = sppr;
//        this.edizm = edizm;
        this.prescribed = prescribed;
        this.dose = dose;
        this.qtydose = qtydose;
        this.qtykurs = qtykurs;
        this.kurs = kurs;

    }

    public PreparatRecept() {

    }

    public long getId() {
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

    public void setPrescribed(int prescribed) {
        this.prescribed = prescribed;
    }
    public int getPrescribed() {
        return prescribed;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }
    public int getDose() {
        return dose;
    }

    public void setQtydose(int qtydose) {
        this.qtydose = qtydose;
    }
    public int getQtydose() {
        return qtydose;
    }

    public void setQtykurs(int qtykurs) {
        this.qtykurs = qtykurs;
    }
    public int getQtykurs() {
        return qtykurs;
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
                this.id,this.prescribed,this.dose,this.qtydose,this.qtykurs,this.kurs
//                ,this.idrec,this.idpre
        );
    }
}
