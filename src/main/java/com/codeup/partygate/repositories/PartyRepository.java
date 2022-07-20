package com.codeup.partygate.repositories;

import com.codeup.partygate.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM partyplanner_db.parties WHERE user_id = ?")
    List<Party> findAllByUserId(long id);

//    Party findByPartyName(String party_name);
//    ArrayList<Party> findAll();

}