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

@Controller
public class PartyController {

    private final PartyRepository partyRepository;

    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping(path = "/parties")
    public String showPartyForm(Model model) {
//        model.addAttribute("party", new Party());
        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
        model.addAttribute("parties", parties);
        return "views/parties";
    }

//    @PostMapping(path = "/party/save")
//    public String saveParty(@ModelAttribute Party party) {
////        if (party.getEvent_id() == null) {
////            party.setEvent_id("0");
////        }
//        partyRepository.save(party);
//
//        return "views/parties";
//    }

//    @GetMapping(path = "/party/{id}")
//    public void showParty(@PathVariable String id, Model model, Request request) {
//        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
//        Party thisParty = null;
//
//        long partyId = Integer.parseInt(id);
//        for (Party party : parties) {
//            if (Objects.equals(party.getParty_id(), partyId)) {
//                thisParty = party;
//            }
//        }
//        model.addAttribute("party", thisParty);
//        HttpSession session = request.getSession();
//        session.setAttribute("party_id", request.getParameter("name"));
////        assert thisParty != null;
////        String html = "/views/party/{" + id + "}";
////        return html;
//    }

//    @GetMapping(path = "/party")
//    public String showNoParty() {
//        return "/views/party";
//    }


    @GetMapping(path = "/party-form")
    public String partyForm (Model model) {
        model.addAttribute("party", new Party());
        return "/views/party-form";
    }



    @PostMapping(path = "/party-form")
    public String partyCreate (@ModelAttribute Party party) {
        partyRepository.save(party);
        return "views/home";
    }

    @GetMapping(path = "/party-select/{id}")
    public String partyPage(@PathVariable long id, Model model) {
//        Party party = partyRepository.findAll(party_id);
//        party = partyRepository.getById(party_id);
//        String partyName = party.getParty_name();
//        String partyDescription = party.getDescription();
        Party party = partyRepository.getById(id);
        model.addAttribute("name", party.getParty_name());
        model.addAttribute("description", party.getDescription());
        return "views/party-select";

    }
}
//        for (Party party: parties
//             )
//        {
//            if (party.getParty_id() == party_id) {
//                model.addAttribute("partyName", party.getParty_name());
//                model.addAttribute("partyDescription", party.getDescription());
//
//            }
//        }
//        model.addAttribute("partyName", thisParty.getParty_name());
//        model.addAttribute("partyDescription", thisParty.getDescription());
//        model.addAttribute("partyid", party.getParty_id());
