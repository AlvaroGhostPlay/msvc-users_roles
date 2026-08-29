package com.example.msvcusers_roles.controllers;

import com.example.msvcusers_roles.models.Role;
import com.example.msvcusers_roles.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return roleService.findById(id).map( role -> ResponseEntity.ok(role))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> findByRole(@PathVariable String role) {
        return roleService.findByRole(role).map( roledb -> ResponseEntity.ok(roledb))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveRole(@RequestBody Role roleRequest) {
        return roleService.createRole(roleRequest).map(role -> ResponseEntity.ok(role))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<?> UpdateRoleById(@RequestBody Role roleRequest, @RequestParam UUID id) {
        return roleService.updateRole(roleRequest, id).map(role -> ResponseEntity.ok(role))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRoleById(@RequestParam UUID id) {
        return roleService.deleteRoleById(id).map(role -> ResponseEntity.ok(role))
                .orElse(ResponseEntity.notFound().build());
    }
}