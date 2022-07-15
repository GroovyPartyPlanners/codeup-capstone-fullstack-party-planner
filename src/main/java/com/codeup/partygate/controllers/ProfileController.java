package com.codeup.partygate.controllers;

import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.UserRepository;
import com.codeup.partygate.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private final UserRepository usersRepository;
    private final UserService userService;

    public ProfileController(UserRepository usersRepository, UserService userService) {
        this.usersRepository = usersRepository;
        this.userService = userService;
    }

    @GetMapping(path = "/profile")
    public String showProfilePage() {
        return "views/profile";
    }

    @GetMapping(path = "/profile/{id}")
    public String viewProfileInfo(@PathVariable long id, Model model) {
        User user = userService.loggedInUser();
        model.addAttribute("user", user);
        return "views/edit-profile";
    }

    @PostMapping(path = "/profile/{id}/update")
    public String updateProfileInfo(@ModelAttribute User user) {
        usersRepository.saveAndFlush(user);
        return "redirect:/profile";
    }

}