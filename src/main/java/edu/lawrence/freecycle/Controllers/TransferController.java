package edu.lawrence.freecycle.Controllers;

import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Classes.Transfer;
import edu.lawrence.freecycle.Services.TransferService;

@RestController
@RequestMapping("/transfers")
@CrossOrigin(origins="*")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    // Save new transfer
    @PostMapping
    public int save(@RequestBody Transfer transfer) {
        service.save(transfer);
        return 1;
    }

    // Update transfer site + time
    @PatchMapping(params={"transferId", "site", "time"})
    public void update(
            @RequestParam int transferId,
            @RequestParam String site,
            @RequestParam String time) {

        service.update(transferId, site, time);
    }

    // Delete transfer
    @DeleteMapping("/{transferId}")
    public void deselect(@PathVariable int transferId) {
        service.deselect(transferId);
    }

    // Complete transfer
    @DeleteMapping(params={"transferId"})
    public void complete(@RequestParam int transferId) {
        service.complete(transferId);
    }

    // Find by recipient
    @GetMapping("/{userId}")
    public Transfer findTransferById(@PathVariable int userId) {
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