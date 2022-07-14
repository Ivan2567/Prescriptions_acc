package com.example.springserver.reps;

import com.example.springserver.entity.*;
import org.springframework.stereotype.*;
import javax.persistence.*;
import javax.transaction.*;
import java.util.List;

//Не рабочий репозиторий
@Repository
@Transactional
public class TestRep {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Patient> getAll() {
        return entityManager.createQuery("from Patient c order by c.id desc", Patient.class).getResultList();
    }

    public Patient getById(int id) {
        return entityManager.find(Patient.class, id);
    }
    public Patient create(Patient patient) {
        entityManager.persist(patient);
        return patient;
    }
    public Doctor getByIdD(int id) {
        return entityManager.find(Doctor.class, id);
    }
    public Recept getByIdR(int id) {
        return entityManager.find(Recept.class, id);
    }
    public Preparat getByIdP(int id) {
        return entityManager.find(Preparat.class, id);
    }
    public PreparatRecept getByIdPR(int id) {
        return entityManager.find(PreparatRecept.class, id);
    }

//    public Country update(int id, Country country) {
//        Country original = entityManager.find(Country.class, id);
//        if (original != null) {
//            original.setName(country.getName());
//            for (City city : original.getCities()) {
//                entityManager.remove(city);
//            }
//            original.getCities().clear();
//            for (City city : country.getCities()) {
//                city.setCountry(original);
//                original.getCities().add(city);
//                entityManager.persist(city);
//            }
//            entityManager.merge(original);
//        }
//        return original;
//    }
}
