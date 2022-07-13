package com.codeup.partygate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PartyController {

    @GetMapping(path = "/party")
    public String showParty() {
        return "views/party";
    }
}