package com.example.demo.rowmapper;

import com.example.demo.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class clientMapper implements RowMapper {
    @Override
    public Client mapRow(ResultSet rs, int row_no) throws SQLException {
        Client client = new Client();
        client.setClient_id(rs.getInt("client_id"));
        client.setClient_name(rs.getString("client_name"));
        client.setAuth_token(rs.getString("auth_token"));
        return client;
    }
}
