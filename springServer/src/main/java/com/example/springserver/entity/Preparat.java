package com.example.springserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "preparat")
public class Preparat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //todo kolvo и подобное поменяй на английские аналоги (kolvo - amount и т. д.)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "medname", nullable = false)
    private String medname;

    @Column(name = "method_of_taking", nullable = false)
    private String methodoftaking;

    @Column(name = "unit", nullable = false)
    private String unit;

    @OneToMany(mappedBy = "preparat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreparatRecept> preparatRecepts;

    public Preparat(int id, int quantity, String medname, String methodoftaking, String unit){
        this.id =id;
        this.medname = medname;
        this.quantity = quantity;
        this.methodoftaking = methodoftaking;
        this.unit = unit;

    }

    public Preparat() {

    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }
    public String getMedname() {
        return medname;
    }

    public void setMethodoftaking(String methodoftaking) {
        this.methodoftaking = methodoftaking;
    }
    public String getMethodoftaking() {
        return methodoftaking;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnit() {
        return unit;
    }


    @Override
    public String toString(){
        return String.format("ID:%s | F:%s | I:%s | O:%s | ecp:%s",
                this.id,this.quantity,this.methodoftaking,this.unit);
    }
}
