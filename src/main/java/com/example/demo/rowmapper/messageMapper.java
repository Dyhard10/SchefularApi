package com.example.demo.rowmapper;

import com.example.demo.model.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class messageMapper implements RowMapper {
    @Override
    public Message mapRow(ResultSet rows, int rowNo) throws SQLException {
        //System.out.println("I AM IN MESSAGE MAPPER");
        Message message=new Message();
        message.setMessage_id(rows.getInt("message_id"));
        message.setMessage(rows.getString("message"));
        message.setScheduled_at(rows.getString("scheduled_at"));
        message.setReciver_no(rows.getString("reciver_no"));
        message.setClient_id(rows.getInt("client_id"));
        message.setCreated_at(rows.getString("created_at"));
        message.setPending_status(rows.getBoolean("pending_status"));
        message.setScheduled_status(rows.getBoolean("scheduled_status"));
        message.setSubmitted_at(rows.getString("submitted_at"));
        message.setSubmitted_status(rows.getBoolean("submitted_status"));
        message.setWhatsapp_api_message_id(rows.getString("whatsapp_api_message_id"));
        return message;


    }
}
