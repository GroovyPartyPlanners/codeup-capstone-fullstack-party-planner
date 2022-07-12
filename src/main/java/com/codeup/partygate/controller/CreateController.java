package com.codeup.partygate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CreateController {

    @GetMapping(path = "/create")
    public String showLanding() {
        return "create";
    }
}