package com.example.demo.dao;

import com.example.demo.exception.SQLException;
import com.example.demo.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MessagedaoTest {
    @Autowired
    Messagedao messagedao;
    @Test
    void saveMessageTest() throws SQLException{
        Message testmessage = new Message(412,"HELLO there i am testing save message","2022-06-24 20:14:30","918529784744",5, LocalDateTime.now().toString(),true,LocalDateTime.now().toString(),true,"something",true);
        int result =this.messagedao.saveMessage(testmessage);
        assertEquals(1,result);
    }


}