package com.example.demo.model;

import com.example.demo.validate.ContactNumberConstraint;

import javax.validation.constraints.NotEmpty;

public class Message {
    private Integer message_id;

    @NotEmpty(message = "Message should not be empty")
    private String message;
    private String scheduled_at;
    @ContactNumberConstraint
    private String reciver_no;
    private Integer client_id;
    private String created_at;
    private boolean Scheduled_status;
    private String submitted_at;
    private boolean submitted_status;
    private String whatsapp_api_message_id;
    private boolean pending_status;
    public Message(){
        super();
    }

    public Message(Integer message_id, String message, String scheduled_at, String reciver_no, Integer client_id, String created_at, boolean scheduled_status, String submitted_at, boolean submitted_status, String whatsapp_api_message_id, boolean pending_status) {
        this.message_id = message_id;
        this.message = message;
        this.scheduled_at = scheduled_at;
        this.reciver_no = reciver_no;
        this.client_id = client_id;
        this.created_at = created_at;
        this.Scheduled_status = scheduled_status;
        this.submitted_at = submitted_at;
        this.submitted_status = submitted_status;
        this.whatsapp_api_message_id = whatsapp_api_message_id;
        this.pending_status = pending_status;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getScheduled_at() {
        return scheduled_at;
    }

    public void setScheduled_at(String scheduled_at) {
        this.scheduled_at = scheduled_at;
    }

    public String getReciver_no() {
        return reciver_no;
    }

    public void setReciver_no(String reciver_no) {
        this.reciver_no = reciver_no;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isScheduled_status() {
        return Scheduled_status;
    }

    public void setScheduled_status(boolean scheduled_status) {
        Scheduled_status = scheduled_status;
    }

    public String getSubmitted_at() {
        return submitted_at;
    }

    public void setSubmitted_at(String submitted_at) {
        this.submitted_at = submitted_at;
    }

    public boolean isSubmitted_status() {
        return submitted_status;
    }

    public void setSubmitted_status(boolean submitted_status) {
        this.submitted_status = submitted_status;
    }

    public String getWhatsapp_api_message_id() {
        return whatsapp_api_message_id;
    }

    public void setWhatsapp_api_message_id(String whatsapp_api_message_id) {
        this.whatsapp_api_message_id = whatsapp_api_message_id;
    }

    public boolean isPending_status() {
        return pending_status;
    }

    public void setPending_status(boolean pending_status) {
        this.pending_status = pending_status;
    }
    @Override
    public String toString() {
        //System.out.println("to string in message model is running");
        return "Message [message_id=" + message_id + ", message=" + message + ", destination_phone_number="
                + reciver_no + ", scheduled_at=" + scheduled_at + ", client_id=" + client_id
                + ", created_at=" + created_at + ",pending_status=" + pending_status + ", scheduled_status=" + Scheduled_status + ", submitted_at="
                + submitted_at + ", submitted_status=" + submitted_status + ", whatsapp_api_message_id="
                + whatsapp_api_message_id + "]";
    }
}
