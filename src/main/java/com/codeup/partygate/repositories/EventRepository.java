package com.codeup.partygate.repositories;


import com.codeup.partygate.models.Event;
import com.codeup.partygate.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EventRepository extends JpaRepository<Event, Long> {
    ArrayList<Party> findAllById(Long eventId);
//    List<Event> findAllByEventId(long id);
}