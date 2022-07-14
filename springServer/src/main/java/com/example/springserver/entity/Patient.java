package com.example.springserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long id;
//todo переделай именование переменных (f - lastName и т. д.)
    //можешь переделать и в таблице и в сущности. Если не хочешь переделывать в таблице
    // то сделай как обычно делают - в сущности Doctor я тебе написал как
    @Column(name = "first_name", nullable = false)
    private String firstname;

    @Column(name = "last_name", nullable = false)
    private String lastname;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "polis", nullable = false)

    private String polis;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recept> recepts;

    public Patient(long id, String firstname, String lastname, String patronymic, String polis){
        this.id =id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.polis = polis;
    }

    public Patient() {

    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getLastname() {
        return lastname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public String getPatronymic() {
        return patronymic;
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
                this.id,this.firstname,this.lastname,this.patronymic,this.polis);
    }
}
