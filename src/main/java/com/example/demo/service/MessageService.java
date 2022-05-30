package com.example.demo.service;

import com.example.demo.dao.Messagedao;
import com.example.demo.exception.SQLException;
import com.example.demo.model.Message;
import com.example.demo.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    Messagedao eDao;
    public MessageService(Messagedao eDao){
        this.eDao=eDao;
    }
    public int saveMessage(Request requestBody) throws SQLException {
        return eDao.save(requestBody);
    }
    public int saveNewMessage(Message message) throws SQLException{
        return eDao.saveMessage(message);
    }

    public int updateMessageStatus(Boolean pending_status, Boolean submited_status, String whatsAppMessageId, LocalDateTime submitted_at, Integer message_id) throws SQLException{
        return eDao.updateMessageStatus(pending_status,submited_status,whatsAppMessageId,submitted_at,message_id);
    }

    public List<Message> getAllMessage() throws SQLException {
        return eDao.getAllMessage();
    }

}
