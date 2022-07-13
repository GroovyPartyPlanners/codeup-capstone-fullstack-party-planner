package com.codeup.partygate.controller;

import com.codeup.partygate.model.UserRepository;
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
