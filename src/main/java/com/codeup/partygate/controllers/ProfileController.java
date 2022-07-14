<<<<<<< HEAD:src/main/java/com/codeup/partygate/controller/ProfileController.java
//package com.codeup.partygate.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class ProfileController {
//
//    @GetMapping(path = "/profile")
//    public String showProfile() {
//        return "profile";
//    }
//}
=======
package com.codeup.partygate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    @GetMapping(path = "/profile")
    public String showProfilePage() {
        return "views/profile";
    }
}
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0:src/main/java/com/codeup/partygate/controllers/ProfileController.java
