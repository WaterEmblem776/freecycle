package edu.lawrence.freecycle.Services;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import java.util.Optional;

import edu.lawrence.freecycle.Classes.User;
import edu.lawrence.freecycle.Repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UUID checkLogin(String username, String password) {
        List<User> existing = repo.findByUsername(username);

        //If the user does not exist, return null
        if (existing == null) {
            return null;
        }

        //If the user does exist and their password does not match, return null
        if (!existing.get(0).getPassword().equals(password)) {
            return null;
        }

        //If their password matches, return the user ID???????
        return existing.get(0).getUserId();
    }
    //This isn't needed - the user should never have access to UUIDs
    //public User findById(UUID id) {
    //    return repo.findById(id).orElse(null);
    //}

    public User createUser(User user) {
        //Optional<User> maybeUser = repo.findById(user.getUserId()); Used for unit testing, not necessary for our current assignment.
        return repo.save(user);
    }

    public void deleteUser(UUID id) {
        repo.deleteById(id);
    }
}