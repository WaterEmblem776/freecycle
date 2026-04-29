package edu.lawrence.freecycle.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping(params={"user", "password"})
    public int checkLogin(@RequestParam(value="user") String user,@RequestParam(value="password") String password)
    {
        User result = dao.findByUsername(user);
        //If result is null or the password doesn't equal the one in the database, return 0
        if(result == null)
            return 0;
        if(!result.getPassword().equals(password))
            return 0;
        return result.getUserId(); //Otherwise, return the ID
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") int id) 
    {
        return dao.findById(id);
    }


}
