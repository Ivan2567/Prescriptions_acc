package com.example.springserver.service;

import com.example.springserver.entity.Patient;
import com.example.springserver.entity.Recept;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class FilterListServiceTest {

    FilterListService filterListServiceMock = Mockito.mock(FilterListService.class);

//    @Before
//    static void setup(){
//
//    }
    @Test
    void filterReceptList() {
        long id = 1;
        Date startdate = new Date(1212121212121L)  ;
        Date enddate = new Date();
        List<Recept> recepts = new ArrayList<>();
        Mockito.when(filterListServiceMock.filterReceptList(id,startdate,enddate,true,true,true)).thenReturn(recepts);
        Assertions.assertEquals( recepts , filterListServiceMock.filterReceptList(id,startdate,enddate,true,true,true));
        Mockito.verify(filterListServiceMock, Mockito.times(1)).filterReceptList(id,startdate,enddate,true,true,true);
    }
}