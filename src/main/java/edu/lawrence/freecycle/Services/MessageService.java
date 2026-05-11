package edu.lawrence.freecycle.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.freecycle.Classes.Message;
import edu.lawrence.freecycle.Repositories.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // save new message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // get all messages for one receiver
    public List<Message> getMessagesByReceiver(int receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }

    // mark message as read
    public Message markAsRead(int id) {
        Message message = messageRepository.findById(id).orElse(null);

        if (message != null) {
            message.setRead(true);
            return messageRepository.save(message);
        }

        return null;
    }
}