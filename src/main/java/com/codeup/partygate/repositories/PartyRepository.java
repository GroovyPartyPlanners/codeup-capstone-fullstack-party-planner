package com.codeup.partygate.repositories;

import com.codeup.partygate.models.Party;
import com.codeup.partygate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {
//    @Query(nativeQuery = true, value = "SELECT * FROM partyplanner_db.parties WHERE user_id = ?")
//    List<Party> findAllByUserId(long id);


    List<Party> findAllByUser(User user);

}