package com.example.msvcusers_roles.dto;

import java.util.Date;

public record UserUpdateDto(
        String username,
        Date updated,
        Boolean changePass,
        Boolean enable,
        String[] roles
) {
}
