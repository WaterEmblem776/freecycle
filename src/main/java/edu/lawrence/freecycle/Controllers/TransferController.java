package edu.lawrence.freecycle.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Classes.Transfer;
import edu.lawrence.freecycle.Classes.User;
import edu.lawrence.freecycle.Repositories.UserRepository;
import edu.lawrence.freecycle.Services.TransferService;
import edu.lawrence.freecycle.Services.ItemService;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/transfers")
@CrossOrigin(origins="*")
public class TransferController {

    private final TransferService service;
    private final UserRepository userRepository;


    public TransferController(
            TransferService service,
            UserRepository userRepository) {

        this.service = service;
        this.userRepository = userRepository;
    }

    // Save new transfer
    @PostMapping
    public Transfer save(
            @RequestBody Transfer transfer,
            Authentication auth) {

        String username = auth.getName();

        List<User> users = userRepository
                .findByUsername(username);

        //If we see that there's no users
        if(users.size() == 0) {
            throw new ResourceNotFoundException("No users found.");
        }

        //transfer.setDonorId(users.get(0).getUserId()); I think this is incorrect
        transfer.setRecipientId(users.get(0).getUserId());

        return service.save(transfer);
    }

    // Update transfer site + time
    @PatchMapping(params={"transferId", "site", "time"})
    public void update(
            @RequestParam UUID transferId,
            @RequestParam String site,
            @RequestParam String time) {

        service.update(transferId, site, time);
    }

    // Delete transfer
    @DeleteMapping("/{transferId}")
    public void deselect(@PathVariable UUID transferId) {
        service.deselect(transferId);
    }

    // Complete transfer
    @DeleteMapping(params={"transferId"})
    public void complete(@RequestParam UUID transferId) {
        service.complete(transferId);
    }

    // Find by recipient
    @GetMapping("/{userId}")
    public Transfer findTransferById(@PathVariable UUID userId) {
        return service.findByRecipientId(userId);
    }

    // Find by site
    @GetMapping(params={"site"})
    public Transfer findTransferBySite(@RequestParam String site) {
        return service.findBySite(site);
    }

    // Find by time
    @GetMapping(params={"time"})
    public Transfer findTransferByTime(@RequestParam String time) {
        return service.findByTime(time);
    }
}