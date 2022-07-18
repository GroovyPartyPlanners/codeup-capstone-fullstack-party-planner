package com.codeup.partygate.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "party_user")
public class Party_User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        protected long user_id;

        @Column(length = 50, nullable = false)
        private String first_name;

        @Column(length = 50, nullable = false)
        private String last_name;

        @Column(length = 75, nullable = false, unique = true)
        private String email;

        @Column(length = 50, nullable = false, unique = true)
        private String username;

        @Column(length = 75, nullable = true)
        private String group_name;

        @NotBlank(message = "Confirm password or enter a new one!")
        @Column(length = 60, nullable = false)
        private String password;

        @Column(length = 100, nullable = true)
        private String user_pic_url;
