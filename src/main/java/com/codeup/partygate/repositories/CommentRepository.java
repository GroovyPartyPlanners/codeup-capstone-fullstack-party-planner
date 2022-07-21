package com.codeup.partygate.repositories;

import com.codeup.partygate.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
