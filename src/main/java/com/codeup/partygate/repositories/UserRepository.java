package com.codeup.partygate.repositories;
import com.codeup.partygate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
<<<<<<< HEAD:src/main/java/com/codeup/partygate/model/UserRepository.java
=======

>>>>>>> 54dc1e7653e7afc1e7b121e3cdbbb137299536f0:src/main/java/com/codeup/partygate/repositories/UserRepository.java
}