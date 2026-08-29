package com.example.msvcusers_roles.dto;

import com.example.msvcusers_roles.models.Role;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record UserResponseDto(
        UUID userId,
        String username,
        Date created,
        Date updated,
        Boolean changePass,
        Boolean enable,
        Set<Role> roles
) {}
