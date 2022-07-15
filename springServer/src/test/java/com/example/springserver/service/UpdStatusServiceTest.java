package com.example.springserver.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class UpdStatusServiceTest {

    UpdStatusService updStatusServiceMock = Mockito.mock(UpdStatusService.class);
    @Test
    void changeStatus() {
        Mockito.when(updStatusServiceMock.changeStatus(1)).thenReturn(true);
        Assertions.assertEquals( true , updStatusServiceMock.changeStatus(1));
        Mockito.when(updStatusServiceMock.changeStatus(2L)).thenReturn(false);
        Assertions.assertNotEquals( true , updStatusServiceMock.changeStatus(2L));
        Mockito.verify(updStatusServiceMock, Mockito.times(1)).changeStatus(2L);
    }
}