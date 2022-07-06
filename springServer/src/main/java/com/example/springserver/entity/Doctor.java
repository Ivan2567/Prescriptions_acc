package com.example.springserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String first_name;
    public String last_name;
    public String patronymic;
    public String ecp;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recept> recepts;

    public Doctor(int id, String first_name, String last_name, String patronymic, String ecp){
        this.id =id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.ecp = ecp;
    }

    public Doctor() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getLast_name() {
        return last_name;
    }

    public void setO(String o) {
        this.patronymic = patronymic;
    }
    public String getO() {
        return patronymic;
    }

    public void setEcp(String ecp) {
        this.ecp = ecp;
    }
    public String getEcp() {
        return ecp;
    }

    @Override
    public String toString(){
        return String.format("ID:%s | first_name:%s | last_name:%s | patronymic:%s | ecp:%s",
                this.id,this.first_name,this.last_name,this.patronymic,this.ecp);
    }
}
