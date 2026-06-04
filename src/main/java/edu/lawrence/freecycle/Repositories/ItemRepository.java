package edu.lawrence.freecycle.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.lawrence.freecycle.Classes.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findByDonorId(UUID donorId);

    List<Item> findByStatus(String status);

}