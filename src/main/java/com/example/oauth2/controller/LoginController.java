package com.example.oauth2.controller;


import com.example.oauth2.dto.SessionUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {


    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("loginUser");

        if(loginUser!=null){
            model.addAttribute("user",loginUser);
        }
        return "/home";
    }

    @GetMapping("/user")
    public String user(Model model){
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("loginUser");
        model.addAttribute("user",loginUser);
        return "/user";
    }

    @GetMapping("/guest")
    public String guest(Model model){

        SessionUser loginUser = (SessionUser) httpSession.getAttribute("loginUser");
        model.addAttribute("user",loginUser);
        return "/guest";
    }

}
