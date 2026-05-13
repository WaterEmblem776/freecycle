package edu.lawrence.freecycle.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByDonorId(int donorId);

    List<Item> findByStatus(String status);
}