package com.codeup.partygate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long eventApiId;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Party> parties;


    public Event() {
    }

    public Event(long id, long eventApiId, List<Party> parties) {
        this.id = id;
        this.eventApiId = eventApiId;
        this.parties = parties;
    }

    public Event(long eventApiId, List<Party> parties) {
        this.eventApiId = eventApiId;
        this.parties = parties;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventApiId() {
        return eventApiId;
    }

    public void setEventApiId(long eventApiId) {
        this.eventApiId = eventApiId;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }
}
