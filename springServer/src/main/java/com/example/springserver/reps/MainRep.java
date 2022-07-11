package com.example.springserver.reps;

import com.example.springserver.entity.Patient;
import com.example.springserver.entity.PreparatRecept;
import com.example.springserver.entity.Recept;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.Security;
import javax.crypto.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class MainRep {

    @PersistenceContext
    private EntityManager entityManager;

    public MainRep() throws NoSuchAlgorithmException {
    }

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
    //QR-code(Готово) : id шифруем, затем генерируем qr
    final String password = "password";
    String salt = KeyGenerators.string().generateKey();
    TextEncryptor encryptor = Encryptors.text(password, salt);
//     private KeyGenerator keygen;
//        private SecretKey deskey;
//        private Cipher c ;
//        private byte[] cipherByte;
//        private boolean flag = true;
////        List<SecretKey> keyList = new ArrayList<>();
//        List<byte[]> bytelist = new ArrayList<>();
//        List<String> cipherlist = new ArrayList<>();
    public  //String
    byte[]
    getQR(int id) throws WriterException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {

//        EncrypDES();
        //if(flag){EncrypDES(); flag = !flag;}
        Recept recept = entityManager.find(Recept.class, id);
        String text = "qr";
        String cipherText = encryptor.encrypt(String.valueOf(recept.id));
        //String decryptedText = encryptor.decrypt(cipherText);
//        if(Objects.equals(recept.qr, "qr"))
//        {
//            String textid = String.valueOf(recept.id);
            System.out.println(cipherText);

//            text = new String(Encrytor(textid), StandardCharsets.UTF_8);
//            //text = new BigInteger(1,Encrytor(textid) ).toString(16);
//            recept.setQr(text);
//            System.out.println(text);
//            System.out.println(Decryptor(bytelist.get(cipherlist.indexOf(text))));
//            entityManager.persist(recept);
//            entityManager.merge(recept);

//        }else
//        {
//            text = recept.qr;
//            System.out.println("else");
//            System.out.println(text);
//            System.out.println(Decryptor(text));
//        }
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        BitMatrix bitMatrix = qrCodeWriter.encode(cipherText, BarcodeFormat.QR_CODE, 350, 350);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        //String pngStr = new String(pngData, StandardCharsets.UTF_8);
        //System.out.println(new BigInteger(1, pngData).toString(16));
        return pngData;
        //return pngStr;
//        new BigInteger(1, byteArray).toString(16);
//        new BigInteger(byteArrayAsString, 16).toByteArray();
    }
    public Recept getQRrecept(String qr){
       // System.out.println(Integer.parseInt(encryptor.encrypt(qr)));
        return entityManager.find(Recept.class, Integer.parseInt(encryptor.decrypt(qr)));
    }

//    public void EncrypDES() throws NoSuchAlgorithmException, NoSuchPaddingException{
//        //Security.addProvider(new com.sun.crypto.provider.SunJCE());
//        keygen = KeyGenerator.getInstance("DES");
//        deskey = keygen.generateKey();
//        c = Cipher.getInstance("DES");
//    }
//
//    public //String
//        byte[]
//    Encrytor(String str) throws InvalidKeyException,
//            IllegalBlockSizeException, BadPaddingException {
//        c.init(Cipher.ENCRYPT_MODE, deskey);
////        keyList.add(deskey);
//        byte[] src = str.getBytes(StandardCharsets.UTF_8);
//
//        //byte[] src = new BigInteger(str, 16).toByteArray();/////////
//        bytelist.add(src);
//        //System.out.println(buff);
//        cipherByte = c.doFinal(src);
//        String cipherStr = new String(cipherByte, StandardCharsets.UTF_8);
//            //String cipherStr = new BigInteger(1, cipherByte).toString(16);
//        cipherlist.add(cipherStr);
//        //cipherlist.add(cipherStr);
//        return cipherByte;
//            //return cipherStr;
//    }
//
//    public //String
//    byte[]
//    Decryptor(
//            //String cipherStr
//            byte[] buff
//    ) throws InvalidKeyException,
//            IllegalBlockSizeException, BadPaddingException {
//        c.init(Cipher.DECRYPT_MODE, deskey);
//        //c.init(Cipher.DECRYPT_MODE, keyList.get(cipherlist.indexOf(cipherStr)));
//        // byte[] buff = cipherStr.getBytes(StandardCharsets.UTF_8);
//        //byte[] buff = new BigInteger(cipherStr, 16).toByteArray();/////////
//        //System.out.println(new BigInteger(1, buff).toString(16));
//        cipherByte = c.doFinal(buff);
//        //String str = new String(cipherByte, StandardCharsets.UTF_8);
//        String str = new BigInteger(1, cipherByte).toString(16);
//        return cipherByte;
//        //return str;
//    }

    //НЦП(Почти готово)

    private SecureRandom secureRandom = new SecureRandom();
    //генерация пары ключей
    private KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
    private KeyPair keyPair = keyPairGenerator.generateKeyPair();
    private List<byte[]> list = new ArrayList<>();
    private String textRec (int id){
        Recept recept = entityManager.find(Recept.class, id);
        String text = "";
        text += recept.getDateof();
        text += recept.getDiagnoz();
        text += String.valueOf(recept.getSrok());
        text += String.valueOf(recept.getDoctor().getId());
        System.out.println(text);
        return text;
    }
    public List<byte[]>  CreateECP(int id) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException
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
        list.add(data);
        list.add(digitalSignature);
        return list;
    }

    public String  GetECP(int id) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        Recept recept = entityManager.find(Recept.class, id);
        recept.setQr(new BigInteger(1, CreateECP(id).get(1)).toString(16));
        entityManager.persist(recept);
        entityManager.merge(recept);
        return recept.getQr();
    }
    public boolean CheckECP(int id) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {

        //создание экземпляра пописи//
        Signature signature2 = Signature.getInstance("SHA256WithDSA");
        //инициализация экземпляра пописи (режим проверки)
        signature2.initVerify(keyPair.getPublic());
        //передача данных для сравнения
        Recept recept = entityManager.find(Recept.class, id);
        signature2.update(new BigInteger(recept.getQr(), 16).toByteArray());
        //передача подписи
        boolean verified = signature2.verify(list.get(1));

        //boolean verified = Objects.equals(new BigInteger(1, CreateECP(id).get(1)).toString(16), recept.qr);
        System.out.println("verified = "+verified);
        return verified;
        }


}
