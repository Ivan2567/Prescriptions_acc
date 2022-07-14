package com.example.springserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recept")
public class Recept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //todo srok, diagnoz и подобное поменяй на английские аналоги
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "dateof", nullable = false)
    private Date dateof;

    @Column(name = "term", nullable = false)
    private int term;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @Column(name = "qr", nullable = false)
    private byte[] qr;
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

    public Recept(int id, Date dateof, int term, String status, String diagnosis, byte[] qr, Doctor doctor, Patient patient){
        this.id =id;
        this.dateof = dateof;
        this.term = term;
        this.status = status;
        this.diagnosis = diagnosis;
        this.qr = qr;
        this.doctor = doctor;
        this.patient = patient;

    }

    public Recept() {

    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDateof(Date dateo) {
        this.dateof = dateo;
    }
    public Date getDateof() {
        return dateof;
    }

    public void setTerm(int term) {
        this.term = term;
    }
    public int getTerm() {
        return term;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setQr(byte[] qr) {
        this.qr = qr;
    }
    public byte[] getQr() {
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
                this.id,this.dateof,this.term,this.status,this.diagnosis,this.qr
//                ,this.iddoc,this.idpac
        );
    }
}
