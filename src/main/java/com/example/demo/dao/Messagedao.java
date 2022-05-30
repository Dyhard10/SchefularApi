package com.example.demo.dao;
//import com.google.gson.Gson;
import com.example.demo.controller.MessageController;
import com.example.demo.exception.SQLException;
import com.example.demo.model.Client;
import com.example.demo.model.Message;
import com.example.demo.model.Request;
import com.example.demo.rowmapper.messageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Messagedao {

    Logger logger = LoggerFactory.getLogger(Messagedao.class);
    @Autowired
    JdbcTemplate jdbcTemplate;


    public int save(Request requestBody)throws SQLException{
        int result=0;
//        logger.info("i am in the dao layer");
//        logger.info("kuch bhi likh phark nahi padta",requestBody.getPhoneNumber());
//        System.out.println(requestBody.getPhoneNumber());
//        System.out.println(requestBody.getMessage());
//        System.out.println(requestBody.getScheduledTime());
        //String query ="insert into message_details(message,scheduled_at,reciver_no,client_id,created_at,pending_status,scheduled_status) values (?,?,?,?,?,?,?)";
        String query ="insert into message_detail(message,scheduled_at,reciver_no,created_at,pending_status,scheduled_status,client_id) values (?,?,?,?,?,?,?)";
//        System.out.println(query);
//        System.out.println(LocalDateTime.now());
        try {

            result = jdbcTemplate.update(query, requestBody.getMessage(), requestBody.getScheduledTime(),
                    requestBody.getPhoneNumber(), LocalDateTime.now(), true,true,1);
            return result;
        }catch (Exception e){
            throw new SQLException("error while doing sql operation");
        }
    }
    public int saveMessage(Message message) throws SQLException{
        // Hardcoded validation of msg and contact no need to convert it in a method of a class
        int result=0;
        System.out.println(message.getMessage());
        String reciverNo=message.getReciver_no();
        String msg=message.getMessage();
        String scheduledTime = message.getScheduled_at();
        //boolean validTime=false;
        System.out.println(msg + " " + reciverNo + " "+ scheduledTime);
        boolean valid=false;
        boolean validmsg=false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(scheduledTime, formatter);
        System.out.println(dateTime+" dateTime");
        boolean validTime=LocalDateTime.now().isBefore(dateTime);

        //System.out.println(validTime);
        // Still working fine but its not following the loose coupling ///
        if(msg.length()>0){
            validmsg=true;
        }
        if(reciverNo.length()==12){
            String regex = "[0-9]+";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(reciverNo);
            valid = m.matches();

        }
        if(valid && validmsg && validTime) {
            String query = "insert into message_detail(message,scheduled_at,reciver_no,client_id,created_at,scheduled_status,submitted_at,submitted_status,whatsapp_api_message_id,pending_status) values (?,?,?,?,?,?,?,?,?,?)";
            try {
                result = jdbcTemplate.update(query, message.getMessage(), message.getScheduled_at(), message.getReciver_no(), message.getClient_id(), LocalDateTime.now(), message.isScheduled_status(),
                        LocalDateTime.now(), true, message.getWhatsapp_api_message_id(), message.isPending_status());
                return result;
            } catch (Exception e) {
                throw new SQLException("error while doing sql operation");

            }
        }
        else{
            throw new SQLException("ERROR IN Message Detail or Contact no or Time");
        }

    }

    public List<Message> getAllMessage() throws SQLException {
        String query = "select * from message_detail where pending_status=True and scheduled_at <=now()";
        System.out.println("I am in Messagedao getAllMessage");
        List<Message> messages = Collections.emptyList();
        try {
            messages = jdbcTemplate.query(query, new messageMapper());
            return messages;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException("error while doing polling");
        }
    }

    public int updateMessageStatus(Boolean pending_status, Boolean submited_status, String whatsAppMessageId, LocalDateTime submitted_at, Integer message_id) {

        String query = "UPDATE message_detail set pending_status = ?, submitted_status=?, submitted_at=?,whatsapp_api_message_id=? where message_id = ?";
        System.out.println("updating message status for messageId " + message_id);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, pending_status, submited_status, submitted_at, whatsAppMessageId, message_id);
            return result;
        } catch (Exception e) {
            return 0;
        }
    }
}
