package com.example.demo.service;

import com.example.demo.dao.clientAutherisationDao;
import com.example.demo.exception.SQLException;
import com.example.demo.model.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AutherisationServiceTest {
    @Autowired
    AutherisationService authservice;
    @MockBean
    clientAutherisationDao clientDao;

    @Test
    void validateTokenTest() throws SQLException{
       Client dummyclient=new Client(1,"client1","autherisetheclientid1");
//        Client auth = this.autherisationService.validatetoken("autherisetheclientid3");
//        //System.out.println(auth);
        Mockito.when(this.clientDao.getClientAuthen("autherisetheclientid1")).thenReturn(dummyclient);
//        assertSame(client,auth);
        Client client = this.authservice.validatetoken("autherisetheclientid1");
        assertSame(dummyclient,client);

    }
    @Test
    void notValidToketest() throws SQLException{
        Client dummyclient=new Client(4,"client4","autherisetheclientid4");
        Mockito.when(this.clientDao.getClientAuthen("autherisetheclientid4")).thenReturn(dummyclient);
        Client client = this.authservice.validatetoken("autherisetheclientid4");
        assertSame(dummyclient,client);
    }

}