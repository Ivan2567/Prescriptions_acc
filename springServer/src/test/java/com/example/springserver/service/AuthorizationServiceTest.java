package com.example.springserver.service;

import com.example.springserver.entity.Patient;
import com.example.springserver.reps.PatientRep;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class AuthorizationServiceTest {

//    @Mock
//    PatientRep patientRep;

    AuthorizationService authorizationService = Mockito.mock(AuthorizationService.class);
   // @InjectMocks
    //AuthorizationService authorizationService = new AuthorizationService();



    @Test
    void auto() {
        List<Patient> patients = Arrays.asList(new Patient(1,"fname","lname","patr","1111111111111111"));
        //MockitoAnnotations.initMocks(this);
        Mockito.when(authorizationService.auto("1111111111111111")).thenReturn(patients);
        Assertions.assertEquals( patients , authorizationService.auto("1111111111111111"));
        Mockito.verify(authorizationService, Mockito.times(1)).auto("1111111111111111");

    }
}