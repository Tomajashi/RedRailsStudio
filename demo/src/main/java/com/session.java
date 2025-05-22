package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
@SpringBootApplication
public class session {
    private ArrayList<String> players = new ArrayList<>();
    public static void main(String[] args) {
        SpringApplication.run(session.class, args);
        //TODO: hier muss die spring boot app gestartet werden und die web socket konfiguration
    }
}
