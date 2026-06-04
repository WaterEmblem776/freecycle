package edu.lawrence.freecycle.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Classes.Interest;
import edu.lawrence.freecycle.Classes.User;
import edu.lawrence.freecycle.Repositories.UserRepository;
import edu.lawrence.freecycle.Services.InterestService;

@RestController
@RequestMapping("/interests")
@CrossOrigin(origins="*")
public class InterestController {

    private final InterestService service;
    private final UserRepository userRepository;

    public InterestController(
            InterestService service,
            UserRepository userRepository) {

        this.service = service;
        this.userRepository = userRepository;
    }

    // Save new interest
    @PostMapping("/{id}/interests")
    public int save(
            @RequestBody Interest interest,
            Authentication auth,
        @PathVariable UUID itemId) {

        String username = auth.getName();

        List<User> users = userRepository
                .findByUsername(username);

        //If we see that there's no users attached to this item
        if(users.size() == 0) {
            throw new ResourceNotFoundException("No users found.");
        }

        interest.setUserId(users.get(0).getUserId());
        interest.setItemId(itemId);

        service.save(interest);

        return 1;
    }

    // Withdraw interest
    @DeleteMapping(params={"interestid"})
    public void withdraw(@RequestParam("interestid") UUID interestid) {
        service.withdraw(interestid);
    }

    // Find all interests for an item
    @GetMapping(params={"itemid"})
    public List<Interest> findInterests(@RequestParam("itemid") UUID itemid) {
        return service.findInterests(itemid);
    }
}