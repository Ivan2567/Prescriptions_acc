package com.example.springserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //todo переделай на Long
    @Column(name = "id", nullable = false)
    public long id;
//todo во всех сущностях используй аннотацию @Column и в ее параметрах задавай
    //имя столбца таблицы. Например:
    /*@Column(name = "first_name")
    public String firstName;*/

    @Column(name = "first_name", nullable = false)
    private String firstname;

    @Column(name = "last_name", nullable = false)
    private String lastname;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "ecp", nullable = false)
    private String ecp;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recept> recepts;

    public Doctor(int id, String first_name, String last_name, String patronymic, String ecp){
        this.id =id;
        this.firstname = first_name;
        this.lastname = last_name;
        this.patronymic = patronymic;
        this.ecp = ecp;
    }

    public Doctor() {

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

    public void setEcp(String ecp) {
        this.ecp = ecp;
    }
    public String getEcp() {
        return ecp;
    }

    @Override
    public String toString(){
        return String.format("ID:%s | first_name:%s | last_name:%s | patronymic:%s | ecp:%s",
                this.id,this.firstname,this.lastname,this.patronymic,this.ecp);
    }
}
