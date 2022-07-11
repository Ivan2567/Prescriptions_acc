package com.example.springserver.controller;

import com.example.springserver.entity.Doctor;
import com.example.springserver.entity.Patient;
import com.example.springserver.entity.PreparatRecept;
import com.example.springserver.entity.Recept;
import com.example.springserver.reps.MainRep;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;

@RestController
@RequestMapping(value = "/server", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {
    @Autowired
    private MainRep mainRep;

    @GetMapping("/patient/{patientPolis}")
    public List<Patient> getPatient(@PathVariable("patientPolis") String polis) {
        return mainRep.Auto(polis);
    }

    @GetMapping("/recept_list/{pacientId}")
    public List<Recept> getRecepts(@PathVariable("pacientId") int id) {
        return mainRep.getRecepts(id);
    }

    @GetMapping("/preparat_list/{receptId}")
    public List<PreparatRecept> getPreps(@PathVariable("receptId") int id) {
        return mainRep.getPreps(id);
    }

    @GetMapping("/getQR/{receptId}")
    public //String
    byte[]
    getQR(@PathVariable("receptId") int id) throws IOException, WriterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        return mainRep.getQR(id);
    }
    @GetMapping("/getQRRecept/{qr}")
    public Recept getQR(@PathVariable("qr") String qr) throws IOException, WriterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        return mainRep.getQRrecept(qr);
    }

    @GetMapping("/createECP/{receptId}")
    public String getECP(@PathVariable("receptId") int id) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return mainRep.GetECP(id);
    }
    @GetMapping("/checkECP/{receptId}")
    public boolean checkECP(@PathVariable("receptId") int id) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return mainRep.CheckECP(id);
    }

}
