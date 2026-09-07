package com.example.msvcusers_roles.services;

import com.example.msvcusers_roles.dto.*;
import com.example.msvcusers_roles.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserResponseDto> findAll();

    Optional<UserResponseDto> findById(UUID id);

    Optional<UserResponseDto> findByUsername(String username);

    Optional<UserResponseDto> createUser(UserCreateDto user);

    Optional<UserResponseDto> createUserByBank(UserCreateDto userRequest);

    Optional<UserResponseDto> updateUserById(UserUpdateDto user, UUID id);

    Optional<User> deleteUserById(UUID id);

    UserValidateResponseDto userPasswordValidation(UserPasswordRequestDto userPasswordRequestDto);
}
