package com.codeup.partygate.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class UserDetailsLoader implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /* ... */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* ... */


        return new UserWithRoles(user, roles.ofUserWith(username)); // This is the only change
    }
}