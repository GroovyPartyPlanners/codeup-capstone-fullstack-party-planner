package com.codeup.partygate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventController {

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "You Did It!  If you just submitted something, it's likely in the DB!  Make sure to refresh!";
    }

    @GetMapping(path = "/event")
    public String showEvent() {
        return "views/event";
    }
}