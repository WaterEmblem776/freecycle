package edu.lawrence.freecycle.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.lawrence.freecycle.Classes.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}