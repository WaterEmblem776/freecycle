package edu.lawrence.freecycle.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Classes.Item;
import edu.lawrence.freecycle.Services.ItemService;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins="*")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    // Save new item
    @PostMapping
    public int save(@RequestBody Item item) {
        service.save(item);
        return 1;
    }

    // Delete item
    @DeleteMapping("/{itemid}")
    public void cancel(@PathVariable int itemid) {
        service.cancel(itemid);
    }

    // Find all items
    @GetMapping
    public List<Item> findItems() {
        return service.findItems();
    }

    // Find one item
    @GetMapping("/{itemid}")
    public Item findItem(@PathVariable int itemid) {
        return service.findItem(itemid);
    }

    // Find items by donor
    @GetMapping(params={"donorid"})
    public List<Item> findItemsByDonorId(@RequestParam int donorid) {
        return service.findItemsByDonorId(donorid);
    }

    // Find items by tags
    @GetMapping(params={"tags"})
    public List<Item> findItemsByTags(@RequestParam List<String> tags) {
        return service.findItemsByTags(tags);
    }

    // Reopen item
    @PatchMapping("/{id}")
    public void makeItemVisible(@PathVariable int id) {
        service.reopenItem(id);
    }
}