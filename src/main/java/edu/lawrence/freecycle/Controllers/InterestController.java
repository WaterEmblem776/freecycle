package edu.lawrence.freecycle.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.freecycle.Classes.Interest;
import edu.lawrence.freecycle.Repositories.InterestDAO;


@RequestMapping("/interests")
@CrossOrigin(origins="*")
@RestController
public class InterestController {
    private final InterestDAO dao;

    //Constructor
    public InterestController (InterestDAO dao)
    {
        this.dao = dao;
    }

    //Start with saving new interests
    @PostMapping
    public int save(@RequestBody Interest interest) 
    {
        dao.save(interest);
        return 1;
    }

    //This method allows an interested party to withdraw an interest.
    @DeleteMapping(params={"interestid"})
    public void withdraw(@RequestParam("interestid") int interestid)
    {
        dao.withdraw(interestid);
    }

    //Finding all interests interested in an item.
    @GetMapping(params={"itemid"})
    public List<Interest> findInterests(@RequestParam("itemid") int itemid) 
    {
        return dao.findInterests(itemid);
    }

}
