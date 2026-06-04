package edu.lawrence.freecycle.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {

    Transfer findByRecipientId(UUID recipientId);

    Transfer findBySite(String site);

    Transfer findByTime(String time);

    //Updating the status of an item after a transfer is created (to t, delisting it) or after the transfer is deleted (to a, relisting it)
    @Modifying
    @Query("UPDATE Item SET status = 't' WHERE id = :id")
    void delistItem(@Param("id") UUID id);

    //Using a subquery here to get the itemid from the transferid
    @Modifying
    @Query("UPDATE Item i SET i.status = 't' WHERE i.id = :itemId") 
    void relistItem(@Param("itemId") UUID itemId);


    //Thanks StackOverflow
    //https://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa

}