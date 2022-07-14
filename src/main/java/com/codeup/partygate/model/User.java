package com.codeup.partygate.model;

import javax.persistence.*;

@Table(name="user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long user_id;

    @Column
    public String username;

    @Column
    public String first;

    @Column
    public String last;

    @Column
    public String password;

    @Column
    public String email;

    @Column
    public String group_name;

//    @Column
//    public String user_pic_url;

    public User() {
    }

    public User(User user) {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

//    public String getUser_pic_url() {
//        return user_pic_url;
//    }
//
//    public void setUser_pic_url(String user_pic_url) {
//        this.user_pic_url = user_pic_url;
//    }
}