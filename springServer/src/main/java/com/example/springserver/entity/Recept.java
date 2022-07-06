package com.example.springserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recept")
public class Recept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String dateof;
    public int srok;
    public String status;
    public String diagnoz;
    public String qr;
    //public int iddoc;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddoc")
    private Doctor doctor;

    @JsonIgnore
    public Doctor getDoctor() {
        return doctor;
    }
    //public int idpac;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idpac")
    private Patient patient;

    @JsonIgnore
    public Patient getPatient() {
        return patient;
    }

    @OneToMany(mappedBy = "recept", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreparatRecept> preparatRecepts;

    public Recept(int id, String dateof, int srok, String status, String diagnoz, String qr, int iddoc, int idpac){
        this.id =id;
        this.dateof = dateof;
        this.srok = srok;
        this.status = status;
        this.diagnoz = diagnoz;
        this.qr = qr;
//        this.iddoc = iddoc;
//        this.idpac = idpac;

    }

    public Recept() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDateof(String dateo) {
        this.dateof = dateo;
    }
    public String getDateof() {
        return dateof;
    }

    public void setSrok(int srok) {
        this.srok = srok;
    }
    public int getSrok() {
        return srok;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setDiagnoz(String diagnoz) {
        this.diagnoz = diagnoz;
    }
    public String getDiagnoz() {
        return diagnoz;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
    public String getQr() {
        return qr;
    }

//    public void setIddoc(int iddoc) {
//        this.iddoc = iddoc;
//    }
//    public int getIddoc() {
//        return iddoc;
//    }
//
//    public void setIdpac(int idpac) {
//        this.idpac = idpac;
//    }
//    public int getIdpac() {
//        return idpac;
//    }

    @Override
    public String toString(){
        return String.format("ID:%s | F:%s | I:%s | O:%s | ecp:%s",
                this.id,this.dateof,this.srok,this.status,this.diagnoz,this.qr
//                ,this.iddoc,this.idpac
        );
    }
}
