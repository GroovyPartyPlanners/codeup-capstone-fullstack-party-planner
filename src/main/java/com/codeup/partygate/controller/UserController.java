package com.codeup.partygate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping(path = "/user")
    public String userShow() {
        return "user";
    }

    @PostMapping(path = "/user/login")
    public String userLogin() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin") && password.equals("password")) {
            response.sendRedirect("/profile");
        } else{
            response.sendRedirect("/login");
        }
    }
}
