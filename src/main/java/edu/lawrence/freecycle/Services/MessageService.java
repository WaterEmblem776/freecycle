package edu.lawrence.freecycle.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.lawrence.freecycle.Classes.Message;
import edu.lawrence.freecycle.Repositories.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public Message saveMessage(Message message) {
        return repo.save(message);
    }

    public List<Message> getMessagesByReceiver(int receiverId) {
        return repo.findByReceiverId(receiverId);
    }

    public Message markAsRead(int id) {
        Message message = repo.findById(id).orElse(null);

        if (message != null) {
            message.setRead(true);
            return repo.save(message);
        }

        return null;
    }
}