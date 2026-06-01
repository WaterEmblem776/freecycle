package edu.lawrence.freecycle.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.freecycle.Classes.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUsername(String username);
}