package com.vshamota.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutUsController {

    @GetMapping("/aboutUs")
    public String getAboutPage(){
        return "user/aboutUs";
    }
    @GetMapping("aboutUs/main")
    public String getMainPage(){
        return "redirect:/mainpage";
    }
}
