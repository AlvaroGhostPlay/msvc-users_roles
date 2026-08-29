package com.example.msvcusers_roles.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "roles",schema = "storedb")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;

    @Column(unique = true)
    @NotBlank
    private String role;

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
