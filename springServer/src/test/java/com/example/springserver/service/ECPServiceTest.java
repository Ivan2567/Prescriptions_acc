package com.example.springserver.service;

import com.example.springserver.entity.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ECPServiceTest {

    ECPService ecpServiceMock = Mockito.mock(ECPService.class);
    long testid = 1;
    @Test
    void textRec() {

        String teststring = "Test";
        Mockito.when(ecpServiceMock.textRec(testid)).thenReturn(teststring);
        Assertions.assertEquals( "Test" , ecpServiceMock.textRec(testid));
        Mockito.verify(ecpServiceMock, Mockito.times(1)).textRec(testid);
    }

    @Test
    void createECP() throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        List<byte[]> list = Arrays.asList(new byte[10],new byte[100]);
        List<byte[]> list2 = Arrays.asList(new byte[10]);
        Mockito.when(ecpServiceMock.createECP(testid)).thenReturn(list);
        Assertions.assertEquals( list , ecpServiceMock.createECP(testid));
        Mockito.when(ecpServiceMock.createECP(testid)).thenReturn(list);
        Assertions.assertNotEquals( list2 , ecpServiceMock.textRec(testid));
        Mockito.verify(ecpServiceMock, Mockito.times(1)).createECP(testid);

    }

    @Test
    void getECP() throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        byte[] test = new byte[10];
        Mockito.when(ecpServiceMock.getECP(testid)).thenReturn(test);
        Assertions.assertEquals( test , ecpServiceMock.getECP(testid));
        Mockito.verify(ecpServiceMock, Mockito.times(1)).getECP(testid);
    }

    @Test
    void checkECP() throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Mockito.when(ecpServiceMock.checkECP(testid)).thenReturn(true);
        Assertions.assertEquals( true , ecpServiceMock.checkECP(testid));
        Mockito.when(ecpServiceMock.checkECP(2L)).thenReturn(false);
        Assertions.assertNotEquals( true , ecpServiceMock.textRec(testid));
        Mockito.verify(ecpServiceMock, Mockito.times(1)).textRec(testid);
    }
}