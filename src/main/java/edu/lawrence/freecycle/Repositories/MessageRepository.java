package edu.lawrence.freecycle.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.lawrence.freecycle.Classes.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByReceiverId(int receiverId);
}