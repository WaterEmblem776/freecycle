package edu.lawrence.freecycle.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.lawrence.freecycle.Classes.Item;
import edu.lawrence.freecycle.Repositories.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    // Save new item
    public void save(Item item) {
        repository.save(item);
    }

    // Delete item
    public void cancel(int itemId) {
        repository.deleteById(itemId);
    }

    // Find all available items
    public List<Item> findItems() {
        return repository.findAll();
    }

    // Find one item
    public Item findItem(int itemId) {
        return repository.findById(itemId).orElse(null);
    }

    // Find all items by donor
    public List<Item> findItemsByDonorId(int donorId) {
        return repository.findByDonorId(donorId);
    }

    // Simple version for tags (returns all for now)
    public List<Item> findItemsByTags(List<String> tags) {
        return repository.findAll();
    }

    // Reopen item
    public void reopenItem(int id) {
        Item item = repository.findById(id).orElse(null);

        if (item != null) {
            item.setStatus("a");
            repository.save(item);
        }
    }
}