package com.codeup.partygate.controllers;

import com.codeup.partygate.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
//    shows about page with String
    public String showAbout(User user, Model model, Errors validation) {

//        User validation
        if (validation.hasErrors()) {
//            adds errors to the model
            model.addAttribute("errors", validation);
//            adds user to model
            model.addAttribute("user", user);
        } else {
//            adds a new User to the model
            model.addAttribute("user", new User());
        }
//        redirects to about us view
        return "views/about";
    }

}