package com.codeup.partygate.controllers;

//import com.codeup.partygate.models.Event;

//import com.codeup.partygate.models.MainEvent;

import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.PartyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private final PartyRepository partyRepository;

    public HomeController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping("/")
    public String viewLandingPage(User user, Model model, Errors validation) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", new User());
        }
        return "views/landing";
    }

    @GetMapping("/home")
//    public String viewHomePage() {

    public String viewHomePage(@ModelAttribute Model model) {
        model.addAttribute("parties", partyRepository.findAll());
//        List<MainEvent> events = new ArrayList<>();
//        model.addAttribute("events", events);
    return "views/home";
    }
}