package edu.lawrence.freecycle.Controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.freecycle.Classes.User;
import edu.lawrence.freecycle.Services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(params={"username", "password"})
    public UUID checkLogin(@RequestParam String username, @RequestParam String password) {
        return service.checkLogin(username, password);
    }

    //No longer needed - the user will never have their or anyone elses' UUID
    //@GetMapping("/{id}")
    //public User findById(@PathVariable UUID id) {
    //    return service.findById(id);
    //}

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
    }
}