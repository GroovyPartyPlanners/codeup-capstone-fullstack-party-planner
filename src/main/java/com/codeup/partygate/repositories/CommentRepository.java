package com.codeup.partygate.repositories;

import com.codeup.partygate.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//  @Query(nativeQuery = true, value = "SELECT * FROM partyplanner_db.comments WHERE party_id = ?")
    List<Comment> findAllByPartyId(long id);
}
