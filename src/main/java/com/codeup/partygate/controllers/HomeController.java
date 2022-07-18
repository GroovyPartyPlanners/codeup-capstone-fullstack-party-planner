package com.codeup.partygate.controllers;

import com.codeup.partygate.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String viewLandingPage(Model model) {
        model.addAttribute("user", new User());
        return "views/landing";
    }

    @GetMapping("/home")
    public String viewHomePage() {
        return "views/home";
    }

}
