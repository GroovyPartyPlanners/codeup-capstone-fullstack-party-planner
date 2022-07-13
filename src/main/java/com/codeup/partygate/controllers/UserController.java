package com.codeup.partygate.controllers;

import com.codeup.partygate.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/user")
    public String userShow() {
        return "user";
    }

    @PostMapping(path= "/user/create")
    public String userLogin() {
        return null;
    }
}



