package com.codeup.partygate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {

    @GetMapping(path = "/event")
    public String showLanding() {
        return "event";
    }
}