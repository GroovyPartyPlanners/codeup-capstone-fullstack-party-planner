package com.codeup.partygate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {

//    serves events view
    @GetMapping( "/event")
    public String showEvent() {
        return "views/event";
    }

}