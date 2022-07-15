package com.codeup.partygate.controllers;

import com.codeup.partygate.models.Party;
import com.codeup.partygate.repositories.PartyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Objects;

@Controller
public class PartyController {

    private final PartyRepository partyRepository;

    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping(path = "/parties")
    public String showPartyForm(Model model) {
        model.addAttribute("party", new Party());
        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
        model.addAttribute("parties" , parties);
        return "views/parties";
    }

    @PostMapping(path = "/parties")
    public String saveParty(@ModelAttribute Party party){
//        if (party.getEvent_id() == null) {
//            party.setEvent_id("0");
//        }
        partyRepository.save(party);
        return "redirect:/parties";
    }

    @GetMapping(path = "/party/{name}")
    public String showParty(@PathVariable String name, Model model) {
        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
        Party thisParty = null;
        for (Party party : parties) {
            if (Objects.equals(party.getParty_name(), name)) {
                thisParty = party;
            }
        }
        model.addAttribute("party", thisParty);
        String html = "redirect: /views/party/{" + thisParty.getParty_name()+ "}";

        return html;
    }


    @GetMapping(path = "/party-form")
    public String partyForm (Model model) {
        model.addAttribute("party", new Party());
        return "/views/party-form";
    }

    @PostMapping(path = "/party-form")
    public String partyCreate (@ModelAttribute Party party) {
        partyRepository.save(party);
        String html = "redirect: /party/{" + party.getParty_name()+ "}";
        return html;
    }
}