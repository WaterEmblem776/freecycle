package edu.lawrence.freecycle.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Classes.Message;
import edu.lawrence.freecycle.Repositories.MessageDAO;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins="*")
public class MessageController {

    private final MessageDAO dao;

    public MessageController(MessageDAO dao) {
        this.dao = dao;
    }

    @PostMapping
    public void saveMessage(@RequestBody Message message) {
        dao.save(message);
    }

    @GetMapping("/{receiverId}")
    public List<Message> getMessages(@PathVariable int receiverId) {
        return dao.findByReceiver(receiverId);
    }
}
