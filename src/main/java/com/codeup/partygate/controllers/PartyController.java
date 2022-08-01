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

//    @PostMapping("event/party/{eventId}")
//    public String eventPartyCreate(@ModelAttribute Model model, Party party, @PathVariable long eventId) {
//        ArrayList<Party> parties = eventsRepository.findAllById(eventId);
//        parties.add(party);
//        Event event = new Event();
//        event.setId(eventId);
//        event.setParties(parties);
//        eventsRepository.save(event);
//        return "views/home";
//    }

    @GetMapping("/party/{id}")
    public String viewPartyDetails(@PathVariable long id, Model model) {
        Party party = partyRepository.getById(id);
        model.addAttribute("party", party);
        model.addAttribute("comments", commentRepository.findAllByPartyId(party.getId()));
        return "views/party-select";
    }

    @GetMapping("/party/{id}/edit")
    public String editPartyForm(@PathVariable long id, Model model) {
        model.addAttribute("party", partyRepository.getById(id));
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
//        ArrayList<Party> parties = eventsRepository.findAllById(eventId);
//        parties.add(party);
//        if (eventsRepository.findAllById(eventId) == null) {
            Event event = new Event();
            event.setEventApiId(eventId);
//        event.setParties(parties);
            event = eventsRepository.save(event);
            User user = userRepository.getById(userService.loggedInUser().getId());
            party.setUser(user);
            party.setEvent(event);
            partyRepository.save(party);
            return "redirect:/parties";
        }
//        User user = userRepository.getById(userService.loggedInUser().getId());
//        party.setUser(user);
//        for (Event event: eventsRepository.findAll()
//             ) {
//            if (event.getEventApiId() == eventId) {
//                party.setEvent(event);
//            }
//        }
//        partyRepository.save(party);
//        return "views/parties";
//    }

    @GetMapping("/parties/{eventId}")
    public String viewParties(Model model, @PathVariable Long eventId) {
        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
        ArrayList<Party> eventParties = new ArrayList<Party>();
        for (Party party: parties
             ) {
            if (party.getEvent().getEventApiId() == (eventId)) {
                eventParties.add(party);
            }
        }
        model.addAttribute("parties", eventParties);
        return "views/parties";
    }
}

//    @GetMapping(path = "parties/{event_id}")
//    public String showEventParties(@PathVariable String event_id, @ModelAttribute Model model) {
//        Long eventLong = Long.getLong(event_id);
//        ArrayList<Event> events = (ArrayList<Event>) eventsRepository.findAllById(Collections.singleton(eventLong));
//        Event thisEvent = events.get(0);
//        long eventId = thisEvent.getId();
//        List<Party> parties = partyRepository.findAll();
//        List<Party> eventParties = null;
//        for (Party party : parties
//        ) {
//            if (party.getId() == eventId) {
//                eventParties.add(party);
//            }
//        }
//        model.addAttribute("event", thisEvent);
////        model.addAttribute("events", events);
//        model.addAttribute("parties", eventParties);
//        return "views/parties";
//    }

//    //  delivers the party-creation form which allows the creation of new parties
//    @GetMapping(path = "/party-form")
//    public String partyForm (Model model) {
//
////        System.out.println("Stuff is going to happen");
//        model.addAttribute("party", new Party());
//        return "/views/party-form";
//    }
//
////    Shows all the parties in an index
////    Creates a new, necessary party object that
////    is included in the anchor for each party
////    which allows navigation to /party-select/{id}, the individual party page
//    @GetMapping(path = "/parties")
//    public String partiesShow(Model model) {
//        model.addAttribute("party", new Party());
//        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
//        model.addAttribute("parties", parties);
//
//        return "views/parties";
//    }
////    allows the storing of new party details
////    redirects to party index page
//    @PostMapping(path = "/party/save")
//    public String partySave(@ModelAttribute Party party) {
////        if (party.getEvent_id() == null) {
////            party.setEvent_id("0");
////        }
//        partyRepository.save(party);
//
//        return "views/parties";
//    }
//
//
//
////    @GetMapping(path = "/party")
////    public String showNoParty() {
////        return "/views/party";
////    }
//
//
////
////    @PostMapping(path = "/party-form")
////    public String partyCreate (@ModelAttribute Party party) {
////        partyRepository.save(party);
////        return "views/home";
////    }
//
////    uses party id
//    @GetMapping(path = "/party-select/{id}")
//    public String partyPage(@PathVariable long id, Model model) {
//        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        Party party = partyRepository.findAll(party_id);
////        party = partyRepository.getById(party_id);
////        String partyName = party.getParty_name();
////        String partyDescription = party.getDescription();
//        Party party = partyRepository.getById(id);
//        ArrayList<Comment> comments = (ArrayList<Comment>) commentRepository.findAll();
//        model.addAttribute("comments", comments);
//        model.addAttribute("user", loggedUser);
//        model.addAttribute("name", party.getParty_name());
//        model.addAttribute("description", party.getDescription());
//        model.addAttribute("id", id);
//        return "views/party-select";
//    }
//
//    @GetMapping(path="/attend/{userId}/{partyId}")
//    public String userAttend(@PathVariable String userId, String partyId) {
//        //        HOW TO JOIN TABLES
////===========================================================================
//        Long user = Integer.toUnsignedLong(Integer.parseInt(userId));
//        Long party = Integer.toUnsignedLong(Integer.parseInt(partyId));
//        User u = userRepository.getById(user);
//        Party p = partyRepository.getById(party);
//
////        System.out.println("user.getUsername() = " + user.getUsername());
////        System.out.println("party.getParty_name() = " + party.getParty_name());
//
//        Set<Party> parties = new HashSet<>();
////        List<User> users = new ArrayList<>();
//        parties.add(p);
////        users.add(u);
////===============
////        p.setAttendees(users);
////        user-party association
//        u.setTailgateParties(parties);
////        System.out.println(party);
////        System.out.println(user);
////        partyRepository.save(p);
//        userRepository.save(u); //persist to the db
//        String redirect = "/views/party-select/" + partyId;
////===========================================================================
//        return redirect;
//    }



//        for (Party party: parties
//             )
//        {
//            if (party.getParty_id() == party_id) {
//                model.addAttribute("partyName", party.getParty_name());
//                model.addAttribute("partyDescription", party.getDescription());
//
//            }
//        }
//        model.addAttribute("partyName", thisParty.getParty_name());
//        model.addAttribute("partyDescription", thisParty.getDescription());
//        model.addAttribute("partyid", party.getParty_id());

//    @GetMapping(path = "/party/{id}")
//    public void showParty(@PathVariable String id, Model model, Request request) {
//        ArrayList<Party> parties = (ArrayList<Party>) partyRepository.findAll();
//        Party thisParty = null;
//
//        long partyId = Integer.parseInt(id);
//        for (Party party : parties) {
//            if (Objects.equals(party.getParty_id(), partyId)) {
//                thisParty = party;
//            }
//        }
//        model.addAttribute("party", thisParty);
//        HttpSession session = request.getSession();
//        session.setAttribute("party_id", request.getParameter("name"));
////        assert thisParty != null;
////        String html = "/views/party/{" + id + "}";
////        return html;
//    }
