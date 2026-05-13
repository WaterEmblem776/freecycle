package edu.lawrence.freecycle.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Interest;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {

    List<Interest> findByItemId(int itemId);

}