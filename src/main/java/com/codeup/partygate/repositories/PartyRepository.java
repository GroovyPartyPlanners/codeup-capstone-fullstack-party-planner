package com.codeup.partygate.repositories;

import com.codeup.partygate.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
//    Party findByPartyName(String party_name);
}