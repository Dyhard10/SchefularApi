package com.example.demo.service;

import com.example.demo.dao.clientAutherisationDao;
import com.example.demo.exception.SQLException;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutherisationService {
    @Autowired
    clientAutherisationDao dao;
    public Client validatetoken(String Auth_token) throws SQLException{
        System.out.println("IN VALIDATION SERVICE LAYER");
        Client client = dao.getClientAuthen(Auth_token);
        return client;
    }

}
