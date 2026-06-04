package edu.lawrence.freecycle.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lawrence.freecycle.Classes.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByReceiverId(UUID receiverId);
}