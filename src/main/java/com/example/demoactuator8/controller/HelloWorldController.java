package com.example.demoactuator8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "hello")
    public String hello() throws InterruptedException {
        Thread.sleep(100000);
        System.out.println(Thread.currentThread().getName());
        return "Hello world";
    }
}
