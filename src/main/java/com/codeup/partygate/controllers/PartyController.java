package com.codeup.partygate.controllers;

import com.codeup.partygate.model.Party;
import com.codeup.partygate.model.PartyRepository;
import com.codeup.partygate.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PartyController {

    private final PartyRepository partyRepository;

    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping(path = "/party")
<<<<<<< HEAD:src/main/java/com/codeup/partygate/controller/PartyController.java
    public String userForm(Model model) {
        model.addAttribute("party", new Party());
        return "party";
    }

    @PostMapping(path = "/party/create")
    public String postPost(@ModelAttribute Party newParty) {
        partyRepository.save(newParty);

        return "/";
    }
}
=======
    public String showParty() {
        return "views/party";
    }


}
>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0:src/main/java/com/codeup/partygate/controllers/PartyController.java
