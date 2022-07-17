package com.codeup.partygate.controllers;

import com.codeup.partygate.models.Party;
import com.codeup.partygate.repositories.PartyRepository;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        model.addAttribute("parties", parties);
        return "views/parties";
    }

    @PostMapping(path = "/parties")
    public String saveParty(@ModelAttribute Party party, HttpServletRequest request) {
//        if (party.getEvent_id() == null) {
//            party.setEvent_id("0");
//        }
        partyRepository.save(party);

        return "views/parties";
    }

    @GetMapping(path = "/party/{id}")
    public void showParty(@PathVariable String id, Model model, Request request) {
        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
        Party thisParty = null;

        long partyId = Integer.parseInt(id);
        for (Party party : parties) {
            if (Objects.equals(party.getParty_id(), partyId)) {
                thisParty = party;
            }
        }
        model.addAttribute("party", thisParty);
        HttpSession session = request.getSession();
        session.setAttribute("party_id", request.getParameter("name"));
//        assert thisParty != null;
//        String html = "/views/party/{" + id + "}";
//        return html;
    }

    @GetMapping(path = "/party")
    public String showNoParty() {
        return "/views/party";
    }


    @GetMapping(path = "/party-form")
    public String partyForm (Model model) {
        model.addAttribute("party", new Party());
        return "/views/party-form";
    }

    @PostMapping(path = "/party-form")
    public String partyCreate (@ModelAttribute Party party) {
        partyRepository.save(party);
        String html = "party/{" + party.getParty_name()+ "}";
        return html;
    }

//    @PostMapping(path = "/party/select")
//    public String
}