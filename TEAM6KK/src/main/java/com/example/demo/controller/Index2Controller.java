package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index2Controller {

    @GetMapping("/index2")
    private String display(){
        return "index2.html";
    }
    
}
