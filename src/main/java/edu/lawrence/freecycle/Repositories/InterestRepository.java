package edu.lawrence.freecycle.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Interest;

@Repository
public interface InterestRepository extends JpaRepository<Interest, UUID> {

    List<Interest> findByItemId(UUID itemId);

}