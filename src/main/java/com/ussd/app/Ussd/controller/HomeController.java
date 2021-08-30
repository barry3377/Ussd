package com.ussd.app.Ussd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value="/")
    public  String home(){
        return "index.html";
    }
    @RequestMapping(value="/lout")
    public  String login(){
        return "/index.html";
    }

}
