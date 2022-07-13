package com.codeup.partygate.controllers;

import com.codeup.partygate.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



//import com.codeup.partygate.model.User;  ==========================
//import com.codeup.partygate.model.PostRepository;


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


//    @PostMapping(path = "/user/login")
//    public String userLogin() {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        if (username.equals("admin") && password.equals("password")) {
//            response.sendRedirect("/profile");
//        } else{
//            response.sendRedirect("/login");
//        }
//        return "event";
//    }
//}

