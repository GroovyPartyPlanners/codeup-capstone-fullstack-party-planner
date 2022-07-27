package com.codeup.partygate.controllers;

import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.PartyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String viewHomePage() {

        return "views/home";
    }
    //        List<Party> parties = partyRepository.findAll();
//        model.addAttribute("parties", parties);

}
