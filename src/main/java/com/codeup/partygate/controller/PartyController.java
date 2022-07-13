package com.codeup.partygate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PartyController {

    @GetMapping(path = "/party")
    public String showParty() {
        return "party";
    }
}