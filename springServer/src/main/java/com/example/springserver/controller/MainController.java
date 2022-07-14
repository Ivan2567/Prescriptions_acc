package com.example.springserver.controller;

import com.example.springserver.entity.Doctor;
import com.example.springserver.entity.Patient;
import com.example.springserver.entity.PreparatRecept;
import com.example.springserver.entity.Recept;
import com.example.springserver.model.FilterReq;
import com.example.springserver.reps.MainRep;
import com.example.springserver.reps.PreparatReceptRep;
import com.example.springserver.reps.ReceptRep;
import com.example.springserver.service.*;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/server", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {
//    @Autowired
//    private MainRep mainRep;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private QRService qrService;
    @Autowired
    private ECPService ecpService;
    @Autowired
    private UpdStatusService updStatusService;
    @Autowired
    private FilterListService filterListService;
    @Autowired
    private ReceptRep receptRep;
    @Autowired
    private PreparatReceptRep preparatReceptRep;

    @GetMapping("/patient/{patientPolis}")
    public List<Patient> getPatient(@PathVariable("patientPolis") String polis) {
        return authorizationService.auto(polis);
    }

    @GetMapping("/recept_list/{pacientId}")
    public List<Recept> getRecepts(@PathVariable("pacientId") long id) {
        return receptRep.findReceptsByPatientId(id);
    }

    @GetMapping("/preparat_list/{receptId}")
    public List<PreparatRecept> getPreps(@PathVariable("receptId") long id) {
        return preparatReceptRep.findPreparatReceptsByReceptId(id);
    }

    @GetMapping("/getQR/{receptId}")
    public //String
    byte[]
    getQR(@PathVariable("receptId") long id) throws IOException, WriterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        return qrService.getQR(id);
    }
    @GetMapping("/getQRRecept/{qr}")
    public Recept getQR(@PathVariable("qr") String qr){
        return qrService.getQRrecept(qr);
    }

    @GetMapping("/createECP/{receptId}")
    public byte[] getECP(@PathVariable("receptId") long id) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return ecpService.getECP(id);
    }
    @GetMapping("/checkECP/{receptId}")
    public boolean checkECP(@PathVariable("receptId") long id) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return ecpService.checkECP(id);
    }
    @GetMapping("/changeStatus/{receptId}")
    public boolean changeStatus(@PathVariable("receptId") long id) {
        return updStatusService.changeStatus(id);
    }
    @GetMapping("/filterRecs/")
    public List<Recept> getFilterRecs(@RequestBody FilterReq filterReq){
        return filterListService.filterReceptList(filterReq.getId(),filterReq.getStartdate(),filterReq.getEnddate(),filterReq.getActiveflag(),filterReq.getDisactiveflag(),filterReq.getTimeoutflag());
    }

}
