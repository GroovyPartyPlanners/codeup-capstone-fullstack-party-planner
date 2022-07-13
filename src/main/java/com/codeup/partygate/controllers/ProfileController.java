package com.codeup.partygate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @GetMapping(path = "/profile")
    public String showProfile() {
        return "views/profile";
    }
}