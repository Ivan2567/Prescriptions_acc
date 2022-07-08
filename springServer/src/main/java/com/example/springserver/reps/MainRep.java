package com.example.springserver.reps;

import com.example.springserver.entity.Patient;
import com.example.springserver.entity.PreparatRecept;
import com.example.springserver.entity.Recept;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.Security;
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
    //QR-code(Тест)
    public byte[] getQR(String text
//            , int width, int height
    ) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 350, 350);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }
    //НЦП(Тест)
    public boolean  ecp() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException
    {
        SecureRandom secureRandom = new SecureRandom();
        //генерация пары ключей
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //создание экземпляра пописи
        Signature signature = Signature.getInstance("SHA256WithDSA");
        //инициализация экземпляра пописи
        signature.initSign(keyPair.getPrivate(), secureRandom);
        //данные для подписи (после будут приниматся методом)
        byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
        //byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("\u01AF");
        //подписывание данных подписью
        signature.update(data);
        //получение ЭЦП
        byte[] digitalSignature = signature.sign();
        //создание экземпляра пописи
        Signature signature2 = Signature.getInstance("SHA256WithDSA");
        //инициализация экземпляра пописи (режим проверки)
        signature2.initVerify(keyPair.getPublic());
        //передача данных для сравнения
        signature2.update(data);
        //передача подписаных данных
        boolean verified = signature2.verify(digitalSignature);
        System.out.println("verified = "+verified);
        return verified;
    }
    //Печатный документ
}
