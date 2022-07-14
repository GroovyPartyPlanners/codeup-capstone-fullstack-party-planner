package com.codeup.partygate.controller;

import com.codeup.partygate.model.Party;
import com.codeup.partygate.model.PartyRepository;
import com.codeup.partygate.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PartyController {

    private final PartyRepository partyRepository;

    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping(path = "/party")
    public String userForm(Model model) {
        model.addAttribute("party", new Party());
        return "party";
    }

    @PostMapping(path = "/party/create")
    public String postPost(@ModelAttribute Party newParty) {
        partyRepository.save(newParty);

        return "/";
    }
}