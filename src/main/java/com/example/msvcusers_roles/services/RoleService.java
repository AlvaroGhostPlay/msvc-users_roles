package com.example.msvcusers_roles.services;

import com.example.msvcusers_roles.models.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(UUID id);
    Optional<Role> findByRole(String role);
    Optional<Role> createRole(Role roleRequest);
    Optional<Role> updateRole(Role role, UUID id);
    Optional<Role> deleteRoleById(UUID id);
}
