package com.example.test.controller;

import com.example.test.demos.web.entity.User;
import com.example.test.server.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam String phoneNumber,
                                         @RequestParam String password,
                                         @RequestParam String userName) {
        return ResponseEntity.ok(userService.register(phoneNumber, password, userName));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String phoneNumber,
                                      @RequestParam String password) {
        return ResponseEntity.ok(userService.login(phoneNumber, password));
    }
}
