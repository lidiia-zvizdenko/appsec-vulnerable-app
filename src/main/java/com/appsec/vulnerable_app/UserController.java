package com.appsec.vulnerable_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // VULNERABILITY: SQLi
    @GetMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        User user = userRepository.findByUsernameSQLi(username, password);
        if (user != null) {
            return "Welcome, " + user.getUsername() + "!";
        }
        return "Wrong username or password";
    }

    // VULNERABILITY: XSS
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "<html><body>Hello, " + name + "!</body></html>";
    }

    // VULNERABILITY: IDOR
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }
}