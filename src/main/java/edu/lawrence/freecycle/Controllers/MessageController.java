package edu.lawrence.freecycle.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Classes.Message;
import edu.lawrence.freecycle.Services.MessageService;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins="*")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        return service.saveMessage(message);
    }

    @GetMapping("/{receiverId}")
    public List<Message> getMessages(@PathVariable int receiverId) {
        return service.getMessagesByReceiver(receiverId);
    }

    @PutMapping("/{id}/read")
    public Message markAsRead(@PathVariable int id) {
        return service.markAsRead(id);
    }
}