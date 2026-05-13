package edu.lawrence.freecycle.Services;

import org.springframework.stereotype.Service;

import edu.lawrence.freecycle.Classes.User;
import edu.lawrence.freecycle.Repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public int checkLogin(String username, String password) {
        User user = repo.findByUsername(username).orElse(null);

        if (user == null) {
            return 0;
        }

        if (!user.getPassword().equals(password)) {
            return 0;
        }

        return user.getUserId();
    }

    public User findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }
}