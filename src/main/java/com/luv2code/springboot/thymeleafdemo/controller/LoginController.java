package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("acess-denied")
    public String acessDenied(){
        return "login/acess-denied";
    }

}
