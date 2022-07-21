package com.codeup.partygate.controllers;

import com.codeup.partygate.models.Party;
import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.CommentRepository;
import com.codeup.partygate.repositories.PartyRepository;
import com.codeup.partygate.repositories.UserRepository;
import com.codeup.partygate.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PartyController {

    private final UserService userService;
    private final PartyRepository partyRepository;
    private final CommentRepository commentRepository;


    public PartyController(PartyRepository partyRepository, UserRepository userRepository, UserService userService, CommentRepository commentRepository) {
       this.userService = userService;
        this.partyRepository = partyRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping(path = "/parties")
    public String showPartyForm(Model model) {
        model.addAttribute("parties", partyRepository.findAll());
        return "views/parties";
    }

    @GetMapping(path = "/party-form")
    public String partyForm (Model model) {
        model.addAttribute("party", new Party());
        return "views/party-form";
    }

    @PostMapping(path = "/party-form")
    public String partyCreate (@ModelAttribute Party party) {
        User user = userService.loggedInUser();
        party.setUser(user);
        partyRepository.save(party);
        return "redirect:/parties";
    }

    // View party details button on parties page - navigates to individual party page
    @GetMapping("/party/{id}")
    public String viewPartyDetails(@PathVariable long id, Model model) {
        Party party = partyRepository.getById(id);
        model.addAttribute("party", party);
        model.addAttribute("comments", commentRepository.findAllByPartyId(party.getId()));
        return "views/party-select";
    }

    // Edit party details button on profile page - navigates to edit-party page
    @GetMapping("/party/{id}/edit")
    public String editPartyForm(@PathVariable long id, Model model) {
        model.addAttribute("party", partyRepository.getById(id));
        return "views/edit-party";
    }

    // Post edits of party to database - updates party
    @PostMapping("/party/{id}/edit")
    public String postEditPartyForm(@PathVariable long id, @ModelAttribute Party party) {
        User user = userService.loggedInUser();
        party.setUser(user);
        partyRepository.saveAndFlush(party);
        return "redirect:/profile";
    }

    // Delete party button on edit-party page - deletes party post
    @PostMapping("party/{id}/delete")
    public String deleteParty(@PathVariable long id) {
        partyRepository.deleteById(id);
        return "redirect:/profile";
    }

}


