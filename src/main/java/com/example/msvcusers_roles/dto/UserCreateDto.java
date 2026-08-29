package com.example.msvcusers_roles.dto;

import com.example.msvcusers_roles.models.Role;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record UserCreateDto(
        UUID userId,
        String username,
        String password,
        Date created,
        Date updated,
        Boolean changePass,
        Boolean enable,
        String[] roles
) {}
