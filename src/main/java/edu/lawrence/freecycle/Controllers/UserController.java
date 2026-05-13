package edu.lawrence.freecycle.Controllers;

import org.springframework.web.bind.annotation.*;

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
    public int checkLogin(@RequestParam String username, @RequestParam String password) {
        return service.checkLogin(username, password);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }
}