package com.example.msvcusers_roles.mapper;

import com.example.msvcusers_roles.dto.UserResponseDto;
import com.example.msvcusers_roles.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
