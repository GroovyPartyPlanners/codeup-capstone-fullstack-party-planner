package com.codeup.partygate.controllers;

import com.codeup.partygate.models.Party;
import com.codeup.partygate.repositories.PartyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class PartyController {

    private final PartyRepository partyRepository;

    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping(path = "/party")
    public String showPartyForm(Model model) {
        model.addAttribute("party", new Party());
        return "views/party";
    }

    @PostMapping("/party")
    public String saveParty(@ModelAttribute Party party){
//        if (party.getEvent_id() == null) {
//            party.setEvent_id("0");
//        }
        partyRepository.save(party);
        return "redirect:/party";
    }
}