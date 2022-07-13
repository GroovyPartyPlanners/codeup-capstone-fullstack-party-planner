package com.codeup.partygate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CreateController {

    @GetMapping(path = "/create")
    public String showCreate() {
        return "create";
    }
}