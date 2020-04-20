package com.vshamota.demo.controller;

import com.vshamota.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final UserRepo userRepo;
    @GetMapping("login/reg")
    public String getRegistrationPage(){
        return "user/registration";
    }

}
