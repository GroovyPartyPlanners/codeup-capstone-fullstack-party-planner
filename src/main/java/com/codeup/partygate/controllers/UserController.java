package com.codeup.partygate.controllers;

import com.codeup.partygate.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserRepository usersRepository;

    public UserController(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping(path = "/sign-up")
    public String showSignUpForm() {
        return "views/sign-up";
    }

}
