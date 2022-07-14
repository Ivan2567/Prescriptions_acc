package com.example.springserver.service;

import com.example.springserver.entity.Patient;
import com.example.springserver.reps.PatientRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {
    @Autowired
    PatientRep patientRep;
    public List<Patient> auto(String polis) {
        //List<Patient> patients =
        return patientRep.findPatientByPolis(polis);
                //entityManager.createQuery("from Patient c where c.polis = '"+polis+"'", Patient.class).getResultList();
    }
}
