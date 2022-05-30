package com.example.demo.controller;

//import com.example.demo.model.Responce;

import com.example.demo.exception.SQLException;
import com.example.demo.model.Client;
import com.example.demo.model.Message;
import com.example.demo.model.Request;
import com.example.demo.model.Response;
import com.example.demo.service.AutherisationService;
import com.example.demo.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    MessageService messageService;
    @Autowired
    AutherisationService autherisationService;




    @GetMapping("/test")
    public Response testRoute() {
        logger.info("in tesing route");
        return new Response(202,"Everything is fine");
    }
    @PostMapping
   public Response storeMessage(@RequestBody Request requestbody){
        System.out.println(requestbody);
        Response response = null;
        try {
            int result = messageService.saveMessage(requestbody);
            logger.info("result here..." + result);
            response = new Response(200, "Message scheduled successfully");
        }catch (SQLException e){
            logger.warn("sql exception occured");
            response = new Response(e.getErrorCode(), e.getErrorMessage());
        }catch (Exception e){
            logger.warn("exception here " + e.getMessage());
            response = new Response(405, "something went wrong!!");
        }
       return response;

   }
    @PostMapping("/newMessage")

    public Response saveNewMessage(@RequestBody Message message){
        Response response=null;
        try {
            //Client client = autherisationService.validatetoken(message.getClient_id());
            int result = messageService.saveNewMessage(message);
            logger.info("result here..." + result);
            response = new Response(200, "Message scheduled successfully");
        }catch (SQLException e){
            logger.warn("sql exception occured");
            response = new Response(e.getErrorCode(), e.getErrorMessage());
        }catch (Exception e){
            logger.warn("exception here " + e.getMessage());
            response = new Response(405, "something went wrong!!");
        }
        return response;

    }

}
