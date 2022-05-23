package com.codegym.furama_resort.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class MainController {
    @GetMapping("/index")
    public String goToHome(){
        return "index";
    }
}
