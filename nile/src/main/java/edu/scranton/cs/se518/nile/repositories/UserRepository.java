package edu.scranton.cs.se518.nile.repositories;

import edu.scranton.cs.se518.nile.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
