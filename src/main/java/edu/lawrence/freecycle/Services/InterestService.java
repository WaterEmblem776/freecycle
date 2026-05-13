package edu.lawrence.freecycle.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.lawrence.freecycle.Classes.Interest;
import edu.lawrence.freecycle.Repositories.InterestRepository;

@Service
public class InterestService {

    private final InterestRepository repository;

    public InterestService(InterestRepository repository) {
        this.repository = repository;
    }

    // Save new interest
    public void save(Interest interest) {
        repository.save(interest);
    }

    // Delete interest by id
    public void withdraw(int interestId) {
        repository.deleteById(interestId);
    }

    // Find all interests for one item
    public List<Interest> findInterests(int itemId) {
        return repository.findByItemId(itemId);
    }
}