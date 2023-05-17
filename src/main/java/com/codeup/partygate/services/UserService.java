package com.codeup.partygate.services;

import com.codeup.partygate.models.User;
import com.codeup.partygate.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private final UserRepository usersRepository;

    public UserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User loggedInUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        return usersRepository.findById(userId).get();
    }
}
