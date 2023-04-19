package com.codeup.partygate.controllers;

import com.codeup.partygate.models.Event;
import com.codeup.partygate.models.Party;
import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.CommentRepository;
import com.codeup.partygate.repositories.EventRepository;
import com.codeup.partygate.repositories.PartyRepository;
import com.codeup.partygate.repositories.UserRepository;
import com.codeup.partygate.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PartyController {

    @Value("${fileStackAPI}")
    private String fileStackAPIKey;

    private final EventRepository eventsRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PartyRepository partyRepository;
    private final CommentRepository commentRepository;

    public PartyController(EventRepository eventsRepository, PartyRepository partyRepository, UserRepository userRepository, CommentRepository commentRepository, UserService userService) {
        this.eventsRepository = eventsRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.partyRepository = partyRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/events/{eventId}")
    public String eventPartyForm(Model model, @PathVariable Long eventId) {
//        event.setId(eventId);
        model.addAttribute("eventId", eventId);
        model.addAttribute("party", new Party());
        return "views/party-form";
    }

    @GetMapping("/party/{id}")
    public String viewPartyDetails(@PathVariable long id, Model model) {
        Party party = partyRepository.getById(id);
        model.addAttribute("party", party);
        model.addAttribute("comments", commentRepository.findAllByPartyId(party.getId()));
        return "views/party";
    }

    @GetMapping("/party/{id}/edit")
    public String editPartyForm(@PathVariable long id, Model model) {
        model.addAttribute("party", partyRepository.getById(id));
        model.addAttribute("fileStackAPI", fileStackAPIKey);
        return "views/edit-party";
    }

    @PostMapping("/party/{id}/edit")
    public String editParty(@ModelAttribute Party party) {
        User user = userService.loggedInUser();
        party.setUser(user);
        partyRepository.saveAndFlush(party);
        return "redirect:/profile";
    }

    @PostMapping("party/{id}/delete")
    public String deleteParty(@PathVariable long id) {
        partyRepository.deleteById(id);
        return "redirect:/profile";
    }

    @GetMapping("/party-form")
    public String viewPartyForm(@ModelAttribute Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("party", new Party());
        model.addAttribute("fileStackAPI", fileStackAPIKey);
        return "views/party-form";
    }

    @PostMapping("/party-form")
    public String postPartyForm(@ModelAttribute Party party, @RequestParam(name = "event-id") long eventId) {
        Event event = new Event();
        event.setEventApiId(eventId);
        event = eventsRepository.save(event);
        User user = userRepository.getById(userService.loggedInUser().getId());
        if (party.getPartyPicUrl().length() == 0) {
            party.setPartyPicUrl("https://picsum.photos/id/158/200/300");
        }
        party.setUser(user);
        party.setEvent(event);
        partyRepository.save(party);
        return "redirect:/parties/" + eventId;
    }

    @GetMapping("/parties/{eventId}")
    public String viewParties(Model model, @PathVariable Long eventId) {
        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
        ArrayList<Party> eventParties = new ArrayList<Party>();
        for (Party party : parties) {
            if (party.getEvent().getEventApiId() == (eventId)) {
                eventParties.add(party);
            }
        }
        model.addAttribute("parties", eventParties);
        return "views/parties";
    }
}
