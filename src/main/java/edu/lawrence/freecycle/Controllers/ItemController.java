package edu.lawrence.freecycle.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Classes.Item;
import edu.lawrence.freecycle.Classes.User;
import edu.lawrence.freecycle.Repositories.UserRepository;
import edu.lawrence.freecycle.Services.ItemService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins="*")
public class ItemController {

    private final ItemService service;
    private final UserRepository userRepository;

    public ItemController(
            ItemService service,
            UserRepository userRepository) {

        this.service = service;
        this.userRepository = userRepository;
    }

    // Save new item
    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody Item item,
            Authentication auth) {

        String username = auth.getName();

        List<User> users = userRepository
                .findByUsername(username);

        //If we see that there's no users attached to this item
        if(users.size() == 0) {
            throw new ResourceNotFoundException("No users found.");
        }

        item.setDonorId(users.get(0).getUserId());

        service.save(item);

        return ResponseEntity.status(HttpStatus.CREATED).body("Item Created");
    }

    // Delete item
    @DeleteMapping("/{itemid}")
    public void cancel(@PathVariable UUID itemid) {
        service.cancel(itemid);
    }

    // Find all items
    @GetMapping
    public List<Item> findItems() {
        return service.findItems();
    }

    // Find one item
    @GetMapping("/{itemid}")
    public Item findItem(@PathVariable UUID itemid) {
        return service.findItem(itemid);
    }

    // Find items by donor
    @GetMapping(params={"donorid"})
    public List<Item> findItemsByDonorId(@RequestParam UUID donorid) {
        return service.findItemsByDonorId(donorid);
    }

    // Find items by tags
    @GetMapping(params={"tags"})
    public List<Item> findItemsByTags(@RequestParam List<String> tags) {
        return service.findItemsByTags(tags);
    }

    //Moved to transfer
    // Reopen item
    //@PatchMapping("/{id}")
    //public void makeItemVisible(@PathVariable UUID id) {
    //    service.reopenItem(id);
    //}

    // Delist item
    //@PatchMapping("/{id}")
    //public void delistItem(@PathVariable UUID id) {
    //    service.delistItem(id);
    //}
    
}