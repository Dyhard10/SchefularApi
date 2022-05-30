package com.example.demo.dao;

import com.example.demo.exception.SQLException;
import com.example.demo.model.Client;
import com.example.demo.rowmapper.clientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class clientAutherisationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public Client getClientAuthen(String auth_token) throws SQLException {
        String query = "Select * from client_detati where auth_token= ?";
        Client client = null;
        try {
            client = (Client) jdbcTemplate.queryForObject(query,new clientMapper(),auth_token);
            System.out.println(client.tostring());
            return client;
        }catch (Exception e){
            System.out.println("Autherisation failed");
            return null;
        }

    }

}
