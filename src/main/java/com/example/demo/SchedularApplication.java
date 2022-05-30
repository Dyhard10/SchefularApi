package com.example.demo;

import com.example.demo.customTimer.customTimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Timer;

@SpringBootApplication
@EnableScheduling
public class SchedularApplication {
	Timer timer = new Timer();
	@Autowired
	customTimerTask task;
	public static void main(String[] args) {
		SpringApplication.run(SchedularApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void executeTimer(){
		System.out.println("in the timer");
		timer.schedule(task, 1000, 3000);
	}

}
