package com.codeup.partygate.services;

import com.codeup.partygate.repositories.PartyRepository;
import org.springframework.stereotype.Service;

@Service
public class PartyDetailsLoader {

    private final PartyRepository partyRepository;

    public PartyDetailsLoader(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }
}