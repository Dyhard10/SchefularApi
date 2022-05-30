package com.example.demo.model;

public class Client {
    private int client_id;
    private String client_name;
    private String auth_token;

    public Client(int client_id, String client_name, String auth_token) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.auth_token = auth_token;
    }
    public Client(){
        super();
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
    public String tostring(){
        return "Client [client_id=" + client_id + ", client_name=" + client_name + ", auth_token=" + auth_token + "]";
    }
}
