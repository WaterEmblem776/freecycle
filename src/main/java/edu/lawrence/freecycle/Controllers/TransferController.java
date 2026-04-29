package edu.lawrence.freecycle.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    @PatchMapping(params={"site", "time"})
    public void update(@RequestParam(value="site") String site,@RequestParam(value="time") String time)
    {
        dao.addSetting(site, time);
    }

    //This method deletes a transfer.
    @DeleteMapping(params={"transferid"})
    public void deselect(@RequestParam(value="transferid") int transferid)
    {
        dao.deselectRecipient(transferid);
    }

}
