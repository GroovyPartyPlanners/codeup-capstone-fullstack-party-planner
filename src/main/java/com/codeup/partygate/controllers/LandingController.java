package com.codeup.partygate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LandingController {

//    private final PostRepository postRepository;


//    public PostController(PostRepository postRepository1) {

//        this.postRepository = postRepository1;
//    }

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello";
    }

    @GetMapping(path = "/landing")
    public String showLanding() {
        return "/landing";
    }
}
