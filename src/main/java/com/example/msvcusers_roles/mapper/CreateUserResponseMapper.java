package com.example.msvcusers_roles.mapper;

import com.example.msvcusers_roles.dto.UserResponseDto;
import com.example.msvcusers_roles.dto.UserValidateResponseDto;
import com.example.msvcusers_roles.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateUserResponseMapper {
    public UserResponseDto createUserResponseDto(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getCreated(),
                user.getUpdated(),
                user.getChangePass(),
                user.getEnable(),
                user.getRoles()
        );
    }

    public List<UserResponseDto> createUserResponseDtos(List<User> users) {
        return users.stream().map(user -> {
            return this.createUserResponseDto(user);
        }).toList();
    }

    public UserValidateResponseDto createUserValidateResponseDto(User user, Boolean authorization) {
        Set<String> roles = user.getRoles().stream()
                .map(
                role -> role.getRole()
        ).collect(Collectors.toSet());

        return  new UserValidateResponseDto(
                authorization,
                user.getUserId(),
                user.getUsername(),
                user.getEnable(),
                user.getChangePass(),
                roles
        );
    }
}
