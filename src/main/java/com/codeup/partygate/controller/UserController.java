package com.codeup.partygate.controller;

import com.codeup.partygate.model.User;
import com.codeup.partygate.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


//import com.codeup.partygate.model.User;  ==========================
//import com.codeup.partygate.model.PostRepository;


@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @GetMapping(path = "/user")
//    public String userShow() {
//
//        return "user";
//    }
    @GetMapping(path="/")
    @ResponseBody
    public String userShow() {
        return "hello";
    }

    @GetMapping(path = "/login")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping(path = "/login/create")
    public String postPost(@ModelAttribute User newUser) {
        userRepository.save(newUser);

        return "login";
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

//    @PostMapping(path = "/user/login")
//    public String userLogin() {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        if (username.equals("admin") && password.equals("password")) {
//            response.sendRedirect("/profile");
//        } else{
//            response.sendRedirect("/login");
//        }
//    }