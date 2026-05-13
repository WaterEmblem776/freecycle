package edu.lawrence.freecycle.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycle.Classes.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    Transfer findByRecipientId(int recipientId);

    Transfer findBySite(String site);

    Transfer findByTime(String time);
}