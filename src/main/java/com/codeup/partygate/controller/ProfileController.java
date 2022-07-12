package com.codeup.partygate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @GetMapping(path = "/profile")
    public String showLanding() {
        return "profile";
    }
}