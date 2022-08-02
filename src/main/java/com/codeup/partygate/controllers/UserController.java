package com.codeup.partygate.controllers;

import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Value("${fileStackAPI}")
    private String fileStackAPIKey;

    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("fileStackAPI", fileStackAPIKey);
        return "views/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "views/sign-up";
        } else {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            if (user.getProfilePicUrl().length() == 0) {
                user.setProfilePicUrl("https://picsum.photos/seed/picsum/200/300?grayscale");
            }
            usersRepository.save(user);
        }
        return "redirect:/login";
    }
}