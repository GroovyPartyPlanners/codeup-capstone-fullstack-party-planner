package com.codeup.partygate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AboutController {

    @GetMapping(path = "/about")
    public String showAbout() {
        return "views/about";
    }
}