package edu.lawrence.freecycle.Controllers;

import org.springframework.web.bind.annotation.*;

import edu.lawrence.freecycle.Security.JwtUtil;
import edu.lawrence.freecycle.Security.LoginRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        // For now:
        // if username + password exist, generate token

        if (request.getUsername() != null && request.getPassword() != null) {
            return jwtUtil.generateToken(request.getUsername());
        }

        return "Invalid login";
    }
}