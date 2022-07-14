package com.example.springserver.reps;

import com.example.springserver.entity.Patient;
import com.example.springserver.entity.PreparatRecept;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRep extends CrudRepository<Patient,Long> {
    List<Patient> findPatientByPolis (String polis);
}
