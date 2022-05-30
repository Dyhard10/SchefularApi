package com.example.demo.dao;

import com.example.demo.exception.SQLException;
import com.example.demo.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class clientAutherisationDaoTest {
    @Autowired
    clientAutherisationDao clientDao;
    @Test
    void tokenTestForAuthen() throws SQLException{
        Client dummyClient =new Client(1,"client1","autherisetheclientid1");
        Client client = this.clientDao.getClientAuthen("autherisetheclientid1");
        System.out.println(client.tostring());
        System.out.println(dummyClient.tostring());
        assertEquals(dummyClient.tostring(),client.tostring());
    }
    @Test
    void tokenTestForAuthenNotEqual() throws SQLException{
        Client dummyClient =new Client(1,"client1","autherisetheclientid1");
        Client client = this.clientDao.getClientAuthen("autherisetheclientid2");
        System.out.println(client.tostring());
        System.out.println(dummyClient.tostring());
        assertNotEquals(dummyClient.tostring(),client.tostring());
    }
}