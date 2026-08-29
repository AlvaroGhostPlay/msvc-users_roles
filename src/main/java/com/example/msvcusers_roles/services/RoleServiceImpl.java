package com.example.msvcusers_roles.services;

import com.example.msvcusers_roles.models.Role;
import com.example.msvcusers_roles.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public Optional<Role> createRole(Role roleRequest) {
        return Optional.of(roleRepository.save(roleRequest));
    }

    @Override
    public Optional<Role> updateRole(Role role, UUID id) {
        Optional<Role> roleDb = roleRepository.findById(id);
        if (roleDb.isPresent()) {
            roleDb.get().setRole(role.getRole());
            roleRepository.save(roleDb.get());
            System.out.printf("Entro");
        }
        return roleDb;
    }

    @Override
    public Optional<Role> deleteRoleById(UUID id) {
        Optional<Role> roleDb = roleRepository.findById(id);
        if (roleDb.isPresent()) {
            roleRepository.deleteById(id);
        }
        return roleDb;
    }
}
