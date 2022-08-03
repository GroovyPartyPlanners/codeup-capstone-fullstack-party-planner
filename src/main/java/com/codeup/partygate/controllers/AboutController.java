package com.codeup.partygate.controllers;

import com.codeup.partygate.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @Value("${fileStackAPI}")
    private String fileStackAPIKey;

    @GetMapping("/about-us")
    public String showAbout(User user, Model model, Errors validation) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", new User());
            model.addAttribute("fileStackAPI", fileStackAPIKey);
        }
        return "views/about-us";
    }

    @GetMapping("/terms")
    public String showTerms() {
        return "views/terms";
    }

}