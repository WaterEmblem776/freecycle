package edu.lawrence.freecycle.services;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.lawrence.freecycle.Classes.User;
import edu.lawrence.freecycle.Classes.UserDTO;
import edu.lawrence.freecycle.Repositories.UserRepository;
import edu.lawrence.freecycle.Services.UserService;

public class UserServiceTests {

    //BIG NOTE: THIS IS NOT NECESSARY FOR THE CURRENT ASSIGNMENT DUE 6/1/2026. IT REMAINS AS A LEARNING EXPERIMENT

    @Mock
    private UserRepository userRepo;

    private User buyer;
    private User seller;
    private User user;
    private UserService userService;

    private UserDTO userDTO;
    
    //Initialize objects we need to test the User service
    @BeforeEach
    public void setup() 
    {
        MockitoAnnotations.openMocks(this);

        //Create a new service
        userService = new UserService(userRepo);
        

        //Create fake Users to be used in testing later
        buyer.setUserId(UUID.randomUUID());
        buyer.setUsername("test");
        buyer.setPassword("123");

        seller.setUserId(UUID.randomUUID());
        seller.setUsername("seller");
        seller.setPassword("1234");

        userDTO = new UserDTO();
        userDTO.setUsername("test");
        userDTO.setPassword("12345");

    }

    //Create a new user and make it a success
    @Test
    public void testCreateNewUser_Success() throws Exception 
    {
        //Mockito.reset()
        //First mock a test that ensures the user doesn't already exist
        Mockito.when(userRepo.findByUsername(buyer.getUsername())).thenReturn(new ArrayList<>());

        //Test the function for saving a new user. Given a new object of type class, we then answer with
        Mockito.when(userRepo.save(ArgumentMatchers.any(User.class))).thenAnswer(invocation -> {
            //Getting an argument
            User arg = invocation.getArgument(0);
            arg.setUserId(buyer.getUserId());
            return arg;
        });

        //String result = userService.createUser(userDTO);

    }




}
