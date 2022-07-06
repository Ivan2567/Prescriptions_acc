package com.example.springserver.controller;

import com.example.springserver.entity.*;
import com.example.springserver.reps.TestRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)

public class TestController {
    @Autowired
    private TestRep testRep;

    @GetMapping("/patients")
    public List<Patient> getAllP() {
        return testRep.getAll();
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPById(@PathVariable("patientId") int id) {
        return testRep.getById(id);
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createP(@RequestBody Patient patient) {
        return testRep.create(patient);
    }

    @GetMapping("/doctor/{doctorId}")
    public Doctor getDById(@PathVariable("doctorId") int id) {
        return testRep.getByIdD(id);
    }
    @GetMapping("/recept/{receptId}")
    public Recept getRById(@PathVariable("receptId") int id) {
        return testRep.getByIdR(id);
    }
    @GetMapping("/preparat/{preparatId}")
    public Preparat getPrById(@PathVariable("preparatId") int id) {
        return testRep.getByIdP(id);
    }
    @GetMapping("/preparatrecept/{preparatreceptId}")
    public PreparatRecept getPRById(@PathVariable("preparatreceptId") int id) {
        return testRep.getByIdPR(id);
    }
}
