package edu.lawrence.freecycle.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Classes.Interest;
import edu.lawrence.freecycle.Services.InterestService;

@RestController
@RequestMapping("/interests")
@CrossOrigin(origins="*")
public class InterestController {

    private final InterestService service;

    public InterestController(InterestService service) {
        this.service = service;
    }

    // Save new interest
    @PostMapping
    public int save(@RequestBody Interest interest) {
        service.save(interest);
        return 1;
    }

    // Withdraw interest
    @DeleteMapping(params={"interestid"})
    public void withdraw(@RequestParam("interestid") int interestid) {
        service.withdraw(interestid);
    }

    // Find all interests for an item
    @GetMapping(params={"itemid"})
    public List<Interest> findInterests(@RequestParam("itemid") int itemid) {
        return service.findInterests(itemid);
    }
}