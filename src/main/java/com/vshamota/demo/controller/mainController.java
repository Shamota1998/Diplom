package com.vshamota.demo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class mainController {
    @GetMapping("/mainpage")
    public String getMainPage() {
//        Boolean rights = false;
//        Set<Role> userRoles = user.getRoles();
//        if(userRoles.contains("ADMIN")){
//            rights=true;
//        }
//        model.addAttribute("rights", rights);
        return "user/main";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "user/login";
    }

    @GetMapping("main/aboutUs")
    public String getAboutUsPage() {
        return "user/aboutUs";
    }
}
