package com.example.springserver.service;

import com.example.springserver.entity.Recept;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class QRServiceTest {

    QRService qrServiceMock = Mockito.mock(QRService.class);
    @Test
    void getQR() throws IllegalBlockSizeException, NoSuchPaddingException, IOException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, WriterException {
        long testid = 1;
        byte[] test = new byte[10];
        Mockito.when(qrServiceMock.getQR(testid)).thenReturn(test);
        Assertions.assertEquals( test , qrServiceMock.getQR(testid));
        Mockito.verify(qrServiceMock, Mockito.times(1)).getQR(testid);
    }

    @Test
    void getQRrecept() {
        String testin = "INPUT";
        Recept recept = new Recept();
        Mockito.when(qrServiceMock.getQRrecept(testin)).thenReturn(recept);
        Assertions.assertEquals( recept , qrServiceMock.getQRrecept(testin));
        Mockito.verify(qrServiceMock, Mockito.times(1)).getQRrecept(testin);
    }
}