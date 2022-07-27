package com.codeup.partygate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")
public class MainEvent {

    @Id
    private long id;

//    @NotBlank
//    @Column(length = 400, nullable = false)
//    private String event_name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mainEvent")
    private List<Party> parties;

    public MainEvent() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public String getEvent_name() {
//        return event_name;
//    }
//
//    public void setEvent_name(String event_name) {
//        this.event_name = event_name;
//    }


}
