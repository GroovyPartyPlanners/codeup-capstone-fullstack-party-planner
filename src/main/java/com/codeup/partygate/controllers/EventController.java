package com.codeup.partygate.controllers;

import com.codeup.partygate.models.Event;
import com.codeup.partygate.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private final EventRepository eventsRepository;

    public EventController(EventRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @PostMapping(path = "/save-events")
    public void eventSave(@ModelAttribute Event event) {
        eventsRepository.save(event);
    }
}