package com.example.app.web;

import com.example.app.model.User;
import com.example.app.model.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    @GetMapping("/users")
    List<UserDto> findAllUsers();

    @GetMapping("/users/{userId}")
    UserDto findUserById(@PathVariable Long userId);

    @PostMapping("/users")
    UserDto createUser(@RequestBody User user);

    @PutMapping("/users/{userId}")
    void updateUser(@PathVariable Long userId, @RequestBody User user);

    @PatchMapping("/users/{userId}")
    void patchUser(@PathVariable Long userId);
}
