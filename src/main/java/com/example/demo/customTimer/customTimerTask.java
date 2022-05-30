package com.example.demo.customTimer;

import com.example.demo.controller.MessageController;
import com.example.demo.exception.SQLException;
import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class customTimerTask extends TimerTask {
    @Autowired

    MessageService messageService;
    public static String encodeParam(String data) {
        String result = "";
        try {
            result = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] getParamsByte(Map<String, Object> params) {
        byte[] result = null;
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(encodeParam(param.getKey()));
            postData.append('=');
            postData.append(encodeParam(String.valueOf(param.getValue())));
        }
        try {
            result = postData.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    Logger logger = LoggerFactory.getLogger(customTimerTask.class);
    public static int i=0;
    @Override
    public void run() {
        //int result=0;
        JdbcTemplate jdbcTemplate;
        System.out.println("Timer ran" + ++i);
        logger.info("Timer task is running");
        //String query="select message from message_detail where client_id=1 and message_id=2";
        List<Message> messageList = null;
        try {
            messageList=messageService.getAllMessage();
            System.out.println(messageList.size());
        }catch (SQLException e){
            System.out.println(e.getErrorMessage());
            return;

        }
        if (messageList.size()==0){
            System.out.println("no message to send");
            return;
        }
        for(Message m: messageList){
            //System.out.println(m.getMessage());
            String msg=m.getMessage();
            String reciverNo=m.getReciver_no();
            //String
            try {
                URL url = new URL("https://api.gupshup.io/sm/api/v1/msg");
                //URLConnection con = url.openConnection();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //HttpURLConnection http = (HttpURLConnection)con;
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("apikey","0ev11wm3upfovr07gontjlcz1lgnqshx");
                con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                con.setRequestProperty("Accept","application/json");
                con.setRequestProperty("cache-control","no-cache");

                OutputStream outputStream = con.getOutputStream();

                HashMap<String,String> message = new HashMap<>();
                message.put("type","text");
                message.put("text",msg);
                Gson gson = new Gson();
                String jsonMessage = gson.toJson(message);
                String jsonString = gson.toJson(message);
                System.out.println(jsonMessage);
                //System.out.println("hello");
                Map<String,Object> sending = new HashMap<>();
                sending.put("source","917834811114");
                sending.put("src.name","DYHARD");
                sending.put("destination",reciverNo);
                sending.put("channel","whatsapp");
                sending.put("message",jsonMessage);
                /////////////////////////////////////////// later convert it to a function
//                StringJoiner sj = new StringJoiner("&");
//                for(Map.Entry<String,String> entry : sending.entrySet())
//                    sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
//                            + URLEncoder.encode(entry.getValue(), "UTF-8"));
//                byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
//                int length = out.length;
                /////////////////////////////////////////////////////////
                outputStream.write(getParamsByte(sending));
                System.out.println("outputstream here... " + outputStream.toString());
                System.out.println(" response code here--> " + con.getResponseCode());
                if (con.getResponseCode() == 202){
                    int result =0;
                    result = messageService.updateMessageStatus(false,true,"something",LocalDateTime.now(),m.getMessage_id());
                    System.out.println(result);
                    if(result>0) {
                        System.out.println("Updated status");
                    }else{
                        System.out.println("ERROR OCCURED");
                    }

                }
            }catch (Exception e){
                logger.warn("exception here jai ho bhai ji " + e.getMessage());
                System.out.println("Exception occured while sending scheduled message");
            }
        }


    }
}
