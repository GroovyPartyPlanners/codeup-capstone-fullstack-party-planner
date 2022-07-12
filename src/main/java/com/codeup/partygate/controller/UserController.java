package com.codeup.partygate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping(path = "/user")
    public String showLanding() {
        return "user";
    }
}
