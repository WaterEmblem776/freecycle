package edu.lawrence.freecycle.Controllers;

import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Security.JwtUtil;
import edu.lawrence.freecycle.Security.LoginRequest;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        // For now:
        // if username + password exist, generate token

        //Aaron notes: I changed this so that we check for the incorrect parameters first, then return an error 401 ResponseEntity
        if (request.getUsername() == null && request.getPassword() == null) {
            return ResponseEntity.status(401).body("Invalid Login Credentials"); //We need to return an error 401 if the login is incorrect https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status/401
        }

        //Changed to return a loginentity 
        String loginToken = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(loginToken); //.ok returns a 200 status code, saying that we're good
    }
}

