package com.vshamota.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeliveryController {
    @GetMapping(value = "/delivery")
    public String getDdeliveryPage(){
        return "user/delivery";
    }
}
