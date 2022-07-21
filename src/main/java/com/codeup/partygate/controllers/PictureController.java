package com.codeup.partygate.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PictureController {

    @Value("${filestack.api.key}")
    private String filestackapi;

    @GetMapping(path = "/add-picture")
    public String addPicture(@ModelAttribute Model model) {
        model.addAttribute("apiKey", filestackapi);

//getmapping code to do stuff
        return "views/add-picture";
    }
}