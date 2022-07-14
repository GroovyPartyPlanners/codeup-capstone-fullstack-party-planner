package com.codeup.partygate.controller;

import com.codeup.partygate.model.User;
import com.codeup.partygate.model.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


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

    @GetMapping(path = "/login")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping(path = "/login/auth")
    public String userAuth(@ModelAttribute User self) {

        List<User> users = userRepository.findAll();

//        User user = users.findByUsername(username);

        User thisUser = userRepository.findByUsername(self.username);

//        hash check password

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