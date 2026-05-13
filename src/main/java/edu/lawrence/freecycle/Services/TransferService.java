package edu.lawrence.freecycle.Services;

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
    public void save(Transfer transfer) {
        repository.save(transfer);
    }

    // Update site + time
    public void update(int transferId, String site, String time) {
        Transfer transfer = repository.findById(transferId).orElse(null);

        if (transfer != null) {
            transfer.setSite(site);
            transfer.setTime(time);
            repository.save(transfer);
        }
    }

    // Delete transfer
    public void deselect(int transferId) {
        repository.deleteById(transferId);
    }

    // Complete transfer (same delete for now)
    public void complete(int transferId) {
        repository.deleteById(transferId);
    }

    // Find by recipient
    public Transfer findByRecipientId(int userId) {
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