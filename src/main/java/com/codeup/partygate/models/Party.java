package com.codeup.partygate.models;

import javax.persistence.*;

@Entity
@Table(name = "parties")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long party_id;

//    @Column(length = 200, nullable = false)
//    private String event_id;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(length = 200, nullable = false)
    private String party_name;

    public Party() {
    }

    //  NEW CONSTRUCTOR for authentication process (login/logout)
    public Party(Party copy) {
        party_id = copy.party_id; // This line is SUPER important! Many things won't work if it's absent
//        event_id = copy.event_id;
        description = copy.description;
        party_name = copy.party_name;
    }

    public long getParty_id() {
        return party_id;
    }

    public void setParty_id(long party_id) {
        this.party_id = party_id;
    }

//    public String getEvent_id() {
//        return event_id;
//    }
//
//    public void setEvent_id(String event_id) {
//        this.event_id = event_id;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    //  OLD CONSTRUCTOR - BEFORE AUTHENTICATION PROCESS
//    public User(long id, String first_name, String last_name, String email, String username, String group_name, String password, String user_pic_url) {
//        this.id = id;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.email = email;
//        this.username = username;
//        this.group_name = group_name;
//        this.password = password;
//        this.user_pic_url = user_pic_url;
//    }
}