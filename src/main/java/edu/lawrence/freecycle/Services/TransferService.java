package edu.lawrence.freecycle.Services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.lawrence.freecycle.Classes.Transfer;
import edu.lawrence.freecycle.Repositories.TransferRepository;

@Service
public class TransferService {

    private final TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    // Save new transfer
    public Transfer save(Transfer transfer) {
        repository.save(transfer);

        //When you create a transfer, you also delist the item involved.
        repository.delistItem(transfer.getItemId());
        return transfer;
    }

    // Update site + time
    public void update(UUID transferId, String site, String time) {
        Transfer transfer = repository.findById(transferId).orElse(null);

        if (transfer != null) {
            transfer.setSite(site);
            transfer.setTime(time);
            repository.save(transfer);
        }
    }

    // Delete transfer
    public void deselect(UUID transferId) {
        repository.relistItem(transferId);
        repository.deleteById(transferId);
        
        //This also frees up the item to have interests created about it
        
    }

    // Complete transfer (same delete for now)
    public void complete(UUID transferId) {
        repository.deleteById(transferId);
    }

    // Find by recipient
    public Transfer findByRecipientId(UUID userId) {
        return repository.findByRecipientId(userId);
    }

    // Find by site
    public Transfer findBySite(String site) {
        return repository.findBySite(site);
    }

    // Find by time
    public Transfer findByTime(String time) {
        return repository.findByTime(time);
    }
}