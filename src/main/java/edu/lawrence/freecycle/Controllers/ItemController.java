package edu.lawrence.freecycle.Controllers;

import java.util.List;

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

import edu.lawrence.freecycle.Classes.Item;
import edu.lawrence.freecycle.Repositories.ItemDAO;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins="*")
public class ItemController {
    private final ItemDAO dao;

    public ItemController(ItemDAO dao) 
    {
        this.dao = dao;
    }

    //First up: saving new items.
    @PostMapping
    public int save(@RequestBody Item item) 
    {
        dao.save(item);
        return 1;
    }

    //Removing an offered item
    @DeleteMapping("/{itemid}")
    public void cancel(@PathVariable("itemid") int itemid)
    {
        dao.cancel(itemid);
    }

    //Finding all available items.
    @GetMapping()
    public List<Item> findItems() 
    {
        return dao.findItems();
    }

    //Finding one particular item by id.
    @GetMapping({"/{itemid}"})
    public Item findItem(@PathVariable("itemid") int itemid) 
    {
        return dao.findItem(itemid);
    }

    //Finding all items posted by one user.
    @GetMapping(params={"donorid"})
    public List<Item> findItemsByDonorId(@RequestParam("donorid") int donorid) 
    {
        return dao.findItemsByDonorId(donorid);
    }

    //Finding all items that match the tags provided.
    @GetMapping(params={"tags"})
    public List<Item> findItemsByTags(@RequestParam("tags") List<String> tags) 
    {
        return dao.findItemsByTags(tags);
    }

    @PatchMapping("/{id}")
    public void makeItemVisible(@PathVariable("id") int id)
    {
        dao.reopenItem(id);
    }
}
