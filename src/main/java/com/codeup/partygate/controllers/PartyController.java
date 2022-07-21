package com.codeup.partygate.controllers;

import com.codeup.partygate.models.Party;
import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.CommentRepository;
import com.codeup.partygate.repositories.PartyRepository;
import com.codeup.partygate.repositories.UserRepository;
import com.codeup.partygate.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PartyController {

    private final UserService userService;
    public final UserRepository userRepository;
    public final PartyRepository partyRepository;
    public final CommentRepository commentRepository;

    public PartyController(PartyRepository partyRepository, UserRepository userRepository, CommentRepository commentRepository, UserService userService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.partyRepository = partyRepository;
        this.commentRepository = commentRepository;
    }

    //    Shows all the parties in an index
    //    Creates a new, necessary party object that
    //    is included in the anchor for each party
    //    which allows navigation to /party-select/{id}, the individual party page
    @GetMapping("/parties")
    public String viewParties(Model model) {
        model.addAttribute("parties", partyRepository.findAll());
        return "views/parties";
    }

    //  delivers the party-creation form which allows the creation of new parties
    @GetMapping(path = "/party-form")
    public String partyForm (Model model) {
        model.addAttribute("party", new Party());
        return "/views/party-form";
    }

    @PostMapping("/party-form")
    public String postPartyForm(@ModelAttribute Party party) {
        User user = userService.loggedInUser();
        party.setUser(user);
        partyRepository.save(party);
        return "redirect:/parties";
    }

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
    public String editParty(@PathVariable long id, @ModelAttribute Party party) {
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

}


//@GetMapping(path="/attend/{userId}/{partyId}")
//public String userAttend(@PathVariable String userId, String partyId) {
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
