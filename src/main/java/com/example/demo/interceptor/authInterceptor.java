package com.example.demo.interceptor;

import com.example.demo.model.Client;
import com.example.demo.model.Response;
import com.example.demo.service.AutherisationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class authInterceptor implements HandlerInterceptor {
    @Autowired
    AutherisationService autherisationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        String token = request.getHeader("authtoken");
        System.out.println(token);
        System.out.println("I AM IN THE AUTH INTERCEPTOR");
        Client client = autherisationService.validatetoken(token);
        if (client==null){
            System.out.println("AUTHERISATION FAILED");
            response.setContentType("application/json");
            response.setStatus(400);
            PrintWriter out = response.getWriter();
            Response resp = new Response(500, "Authentication failed");
            String responseString = new ObjectMapper().writeValueAsString(resp);
            out.print(responseString);
            return false;
        }
        System.out.println("AUTHERISATION SUCESSFULL");
        return true;


    }
}
