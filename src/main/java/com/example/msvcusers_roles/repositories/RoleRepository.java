package com.example.msvcusers_roles.repositories;

import com.example.msvcusers_roles.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(String role);
    Set<Role> findAllByRoleIn(Collection<String> roles);
}
