package com.codeup.partygate.models;

import javax.persistence.*;


@Table(name="user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long user_id;

    @Column
    public String username;


    @Table(name = "posts")
    @Entity
    public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long id;

        @Column
        public String title;


        @Column
        public String description;

        public Post() {

        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}