package com.example.springserver.reps;

import com.example.springserver.entity.Patient;
import com.example.springserver.entity.PreparatRecept;
import com.example.springserver.entity.Recept;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MainRep {

    @PersistenceContext
    private EntityManager entityManager;
    //Авторизация
    public List<Patient> Auto(String polis) {
        return entityManager.createQuery("from Patient c where c.polis = '"+polis+"'", Patient.class).getResultList();
    }
    //Список рецептов по id пациента
    public List<Recept> getRecepts(int id) {
        return entityManager.createQuery("from Recept c where c.patient.id = "+id+" order by c.id desc", Recept.class).getResultList();
    }
    //Список лекарств по рецепту по id рецепта
    public List<PreparatRecept> getPreps(int id) {
        return entityManager.createQuery("from PreparatRecept c where c.recept.id = "+id+" order by c.id desc", PreparatRecept.class).getResultList();
    }
    //QR-code
    //НЦП
    //Печатный документ
}
