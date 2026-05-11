package edu.lawrence.freecycle.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.freecycle.Classes.Transfer;
import edu.lawrence.freecycle.Repositories.TransferDAO;

@RestController
@RequestMapping("/transfers")
@CrossOrigin(origins="*")
public class TransferController {
    private final TransferDAO dao;

    //Constructor.
    public TransferController(TransferDAO dao)
    {
        this.dao = dao;
    }

    //Save new transfers
    @PostMapping
    public int save(@RequestBody Transfer transfer) 
    {
        dao.save(transfer);
        return 1;
    }

    //This is used to update the site and time of the scheduled transfer.
    @PatchMapping(params={"transferId", "site", "time"})
    public void update(@RequestParam(value="transferId") int transferId,@RequestParam(value="site") String site,@RequestParam(value="time") String time)
    {
        dao.addSetting(site, time, transferId);
    }

    //This method deletes a transfer and reopens the item to be searched for again.
    @DeleteMapping("/{transferId}")
    public void deselect(@PathVariable("transferId") int transferId)
    {
        dao.deselectRecipient(transferId);
    }

    //This deletes the transfer and the original item. Used when the item is given away for good.
    @DeleteMapping(params={"transferId"})
    public void complete(@RequestParam(value="transferId") int transferId)
    {
        dao.completeTransfer(transferId);
    }

    //Find a transfer by user
    @GetMapping("/{userId}")
    public Transfer findTransferById(@PathVariable("userId") int userId)
    {
        return dao.findById(userId);
    }

    //Find a transfer by site
    @GetMapping("/{site}")
    public Transfer findTransferBySite(@PathVariable("site") String site)
    {
        return dao.findBySite(site);
    }

    //Find a transfer by site
    @GetMapping("/{time}")
    public Transfer findTransferByTime(@PathVariable("time") String time)
    {
        return dao.findByTime(time);
    }

}
