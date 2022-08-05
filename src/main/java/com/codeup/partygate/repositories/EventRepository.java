package com.codeup.partygate.repositories;


import com.codeup.partygate.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EventRepository extends JpaRepository<Event, Long> {
    ArrayList<Event> findAllById(Long eventId);
}