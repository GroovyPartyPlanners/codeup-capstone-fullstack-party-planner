package com.codeup.partygate.controllers;

import com.codeup.partygate.models.Comment;
import com.codeup.partygate.models.Party;
import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.CommentRepository;
import com.codeup.partygate.repositories.PartyRepository;
import com.codeup.partygate.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {

    private final UserService userService;
    private final PartyRepository partyRepository;
    private final CommentRepository commentRepository;

    public CommentController(UserService userService, PartyRepository partyRepository, CommentRepository commentRepository) {
        this.userService = userService;
        this.partyRepository = partyRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/party/{id}/comment-form")
    public String viewCommentForm(Model model, @PathVariable long id) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("party", partyRepository.getById(id));
        return "views/comment-form";
    }

    @PostMapping("/party/{id}/comment-form")
    public String postCommentForm(@PathVariable long id, @ModelAttribute Comment formComment, @Valid Comment comment_content, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("comment_content", comment_content);
        } else {
            Comment comment = new Comment(formComment.getComment_content());
            User user = userService.loggedInUser();
            comment.setUser(user);
            Party party = partyRepository.getById(id);
            comment.setParty(party);
            commentRepository.save(comment);
        }
        return "redirect:/party/" + id;
    }

    @GetMapping("/comment/{id}/edit")
    public String editCommentForm(@PathVariable long id, Model model) {
        model.addAttribute("comment", commentRepository.getById(id));
        return "views/edit-comment";
    }

    @PostMapping("/comment/{commentId}/{partyId}/edit")
    public String editComment(@ModelAttribute Comment comment, @PathVariable long commentId, @PathVariable long partyId) {
        comment.setId(commentId);
        User user = userService.loggedInUser();
        comment.setUser(user);
        Party party = partyRepository.getById(partyId);
        comment.setParty(party);
        commentRepository.saveAndFlush(comment);
        return "redirect:/party/" + partyId;
    }

    @PostMapping("comment/{id}/delete")
    public String deleteComment(@PathVariable long id) {
        commentRepository.deleteById(id);
        return "redirect:/party/" + id;
    }

}
