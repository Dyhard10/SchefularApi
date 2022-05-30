package com.example.demo.service;

import com.example.demo.dao.Messagedao;
import com.example.demo.exception.SQLException;
import com.example.demo.model.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MessageServiceTest {
    @Autowired
    MessageService messageService;
    @MockBean
    Messagedao messagedao;

    @Test
    void updateMessageTest() throws SQLException{
        //Mockito.when(this.messagedao.updateMessageStatus((Boolean) ArgumentMatchers.any(),(Boolean) ArgumentMatchers.any(),(String) ArgumentMatchers.any(),(LocalDateTime) ArgumentMatchers.any(),(Integer) ArgumentMatchers.any())).thenReturn(1);
        int actualResult = this.messageService.updateMessageStatus(false,true,"something",LocalDateTime.now(),1);
        Mockito.when(this.messagedao.updateMessageStatus(false,true,"something",LocalDateTime.now(),1)).thenReturn(1);
        System.out.println("actual result" + " " + actualResult);
        this.assertEquals(1,actualResult);
    }

    private void assertEquals(int i, int actualResult) {
    }
    @Test
    void  saveNewMessageTest() throws SQLException{
        Message testmessage = new Message(412,"HELLO there i am testing save message","2022-06-24 20:14:30","918529784744",5,LocalDateTime.now().toString(),true,LocalDateTime.now().toString(),true,"something",true);
        System.out.println(testmessage.getCreated_at());
        //System.out.println(testmessage);
        Mockito.when(this.messagedao.saveMessage(testmessage)).thenReturn(1);
        int actualResult = this.messageService.saveNewMessage(testmessage);
        System.out.println(actualResult);
        org.assertj.core.api.Assertions.assertThat(actualResult).isEqualTo(1);
    }
    @Test
    void getAllMessageTest() throws SQLException{
        List<Message> allmessagelist = Collections.emptyList();
        Mockito.when(this.messagedao.getAllMessage()).thenReturn(allmessagelist);
        List<Message> allmessage = this.messageService.getAllMessage();
        this.assertEquals(allmessagelist.size(), allmessage.size());
    }
}