package edu.lawrence.freecycle.Controllers;

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
import edu.lawrence.freecycle.Repositories.UserDAO;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UserController {
    private final UserDAO dao;

    //Yup. It's literally the same constructor all the other ones.
    public UserController(UserDAO dao) 
    {
        this.dao = dao;
    }

    //In order to check if the user is valid
    @GetMapping(params={"username", "password"})
    public int checkLogin(@RequestParam(value="username") String username,@RequestParam(value="password") String password)
    {
        User result = dao.findByUsername(username);
        //If result is null or the password doesn't equal the one in the database, return 0
        if(result == null)
            return 0;
        if(!result.getPassword().equals(password))
            return 0;
        return result.getUserId(); //Otherwise, return the ID
    }

    //Return one user, searching by id
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") int id) 
    {
        return dao.findById(id);
    }

    //This is for creating a new user.
    @PostMapping
    public void createUser(@RequestBody User user)
    {
        dao.createUser(user);
    }

    //This is for deleting users.
    @DeleteMapping(params={"username", "password"})
    public void deleteUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password)
    {
        dao.deleteUser(username, password);
    }


}
