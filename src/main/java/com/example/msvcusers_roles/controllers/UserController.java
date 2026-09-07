package com.example.msvcusers_roles.controllers;

import com.example.msvcusers_roles.dto.UserCreateDto;
import com.example.msvcusers_roles.dto.UserPasswordRequestDto;
import com.example.msvcusers_roles.dto.UserUpdateDto;
import com.example.msvcusers_roles.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/auth/user")
    public ResponseEntity<?> authorizationUserByLogin(@RequestBody UserPasswordRequestDto userPasswordRequestDto) {
        return ResponseEntity.ok(userService.userPasswordValidation(userPasswordRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto userRequest) {
        return userService.createUser(userRequest)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/new")
    public ResponseEntity<?> createUserByBank(@RequestBody UserCreateDto userRequest) {
        return userService.createUserByBank(userRequest)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto userRequest, @RequestParam UUID id) {
        return userService.updateUserById(userRequest, id)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestParam UUID id) {
        return userService.deleteUserById(id)
                .map(user -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
