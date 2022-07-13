package com.codeup.partygate.controllers;

import com.codeup.partygate.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @GetMapping(path = "/sign-up")
    public String showSignUpForm() {
        return "views/sign-up";
    }

}



