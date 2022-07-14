package com.example.springserver.service;

import com.example.springserver.entity.Recept;
import com.example.springserver.reps.ReceptRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ECPService {
    @Autowired
    ReceptRep receptRep;
    private SecureRandom secureRandom = new SecureRandom();
    //генерация пары ключей
    private KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
    private KeyPair keyPair = keyPairGenerator.generateKeyPair();
    private List<byte[]> list = new ArrayList<>();

    public ECPService() throws NoSuchAlgorithmException {
    }

    private String textRec (long id){
        Recept recept = receptRep.findReceptById(id);
        String text = "";
        text += recept.getDateof();
        text += recept.getDiagnosis();
        text += String.valueOf(recept.getTerm());
        text += String.valueOf(recept.getDoctor().getId());
        System.out.println(text);
        return text;
    }
    public List<byte[]>  createECP(long id) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException
    {

        String text = textRec(id);

        //создание экземпляра пописи
        Signature signature = Signature.getInstance("SHA256WithDSA");
        //инициализация экземпляра пописи
        signature.initSign(keyPair.getPrivate(), secureRandom);
        //данные для подписи (после будут приниматся методом)
        byte[] data = text.getBytes("UTF-8");
        //подписывание данных подписью
        signature.update(data);
        //получение ЭЦП
        byte[] digitalSignature = signature.sign();
        list.clear();
        list.add(data);
        list.add(digitalSignature);
        return list;
    }

    public byte[] getECP(long id) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        Recept recept = receptRep.findReceptById(id);
        recept.setQr(createECP(id).get(1));
        receptRep.save(recept);
        //entityManager.persist(recept);
        //entityManager.merge(recept);
        return recept.getQr();
    }
    public boolean checkECP(long id) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {

        //создание экземпляра пописи//
        Signature signature2 = Signature.getInstance("SHA256WithDSA");
        //инициализация экземпляра пописи (режим проверки)
        signature2.initVerify(keyPair.getPublic());
        //передача данных для сравнения
        //Recept recept = receptRep.findReceptById(id);
        signature2.update(createECP(id).get(0));
        //передача подписи
        boolean verified = signature2.verify(list.get(1));

        //boolean verified = Objects.equals(new BigInteger(1, CreateECP(id).get(1)).toString(16), recept.qr);
        System.out.println("verified = "+verified);
        return verified;
    }
}
