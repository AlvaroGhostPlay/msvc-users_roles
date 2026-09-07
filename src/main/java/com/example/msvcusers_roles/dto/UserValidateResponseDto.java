package com.example.msvcusers_roles.dto;

import java.util.Set;
import java.util.UUID;

public record UserValidateResponseDto (
        Boolean authenticated,
        UUID userId,
        String username,
        Boolean enabled,
        Boolean changePass,
        Set<String> roles
){
}
