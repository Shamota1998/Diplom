package com.vshamota.demo.controller;

import com.vshamota.demo.domain.User;
import com.vshamota.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final UserRepo userRepo;
    @GetMapping("login/reg")
    public String getRegistrationPage(){
        return "user/registration";
    }

//    @PostMapping("user/login")
//    public String logUser(User user, Model model){
//        Set<User> users = (Set<User>) userRepo.findAll();
//        if(users.contains(user)){
//            return "redirect:/user/main";
//        }
//        model.addAttribute("LoginFailed", "Login or Password is incorrect!");
//        return "user/login";
//    }
}
