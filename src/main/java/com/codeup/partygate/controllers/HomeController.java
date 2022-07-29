package com.codeup.partygate.controllers;

import com.codeup.partygate.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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

    @GetMapping("/4xx")
    public String errorPageFour() {
        return "redirect: /views/4xx.html";
    }

    @GetMapping("/5xx")
    public String errorPageFive() {
        return "redirect: /views/5xx.html";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "redirect: /views/error";
    }
}