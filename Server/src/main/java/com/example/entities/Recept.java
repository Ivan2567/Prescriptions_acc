package com.example.entities;

public class Recept {
    public int id;
    public String dateo;
    public int srok;
    public String status;
    public String diagnoz;
    public String qr;
    public int iddoc;
    public int idpac;

    public Recept(int id, String dateo, int srok, String status, String diagnoz, String qr, int iddoc, int idpac){
        this.id =id;
        this.dateo = dateo;
        this.srok = srok;
        this.status = status;
        this.diagnoz = diagnoz;
        this.qr = qr;
        this.iddoc = iddoc;
        this.idpac = idpac;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDateo(String dateo) {
        this.dateo = dateo;
    }
    public String getDateo() {
        return dateo;
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

    public void setIddoc(int iddoc) {
        this.iddoc = iddoc;
    }
    public int getIddoc() {
        return iddoc;
    }

    public void setIdpac(int idpac) {
        this.idpac = idpac;
    }
    public int getIdpac() {
        return idpac;
    }

    @Override
    public String toString(){
        return String.format("ID:%s | F:%s | I:%s | O:%s | ecp:%s",
                this.id,this.dateo,this.srok,this.status,this.diagnoz,this.qr,this.iddoc,this.idpac);
    }

}
