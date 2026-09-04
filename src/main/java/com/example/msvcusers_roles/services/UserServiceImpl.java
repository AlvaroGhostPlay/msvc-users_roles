package com.example.msvcusers_roles.services;

import com.example.msvcusers_roles.dto.UserCreateDto;
import com.example.msvcusers_roles.dto.UserResponseDto;
import com.example.msvcusers_roles.dto.UserUpdateDto;
import com.example.msvcusers_roles.mapper.CreateUserResponseMapper;
import com.example.msvcusers_roles.models.Role;
import com.example.msvcusers_roles.models.User;
import com.example.msvcusers_roles.repositories.RoleRepository;
import com.example.msvcusers_roles.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateUserResponseMapper createUserResponseMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return createUserResponseMapper.createUserResponseDtos(users);
    }

    @Transactional
    @Override
    public Optional<UserResponseDto> findById(UUID id) {
        return userRepository.findById(id).map(createUserResponseMapper::createUserResponseDto);
    }

    @Transactional
    @Override
    public Optional<UserResponseDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(createUserResponseMapper::createUserResponseDto);
    }

    @Transactional
    @Override
    public Optional<UserResponseDto> createUser(UserCreateDto userRequest) {
        Set<Role> roles = roleRepository.findAllByRoleIn(Set.of(userRequest.roles()));
        User user = new User();
        user.setUserId(userRequest.userId());
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setChangePass(userRequest.changePass());
        user.setRoles(roles);
        user.setEnable(userRequest.enable());
        User userCreated = userRepository.save(user);
        if (userCreated != null) {
            return Optional.of(createUserResponseMapper.createUserResponseDto(userCreated));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<UserResponseDto> createUserByBank(UserCreateDto userRequest) {
        Set<Role> roles = new HashSet<>(roleRepository.findByRole("ROLE_USER")
                .map(role -> Set.of(role)).get());

        User user = new User();
        user.setUserId(userRequest.userId());
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setChangePass(userRequest.changePass());
        user.setRoles(roles);
        user.setEnable(userRequest.enable());
        User userCreated = userRepository.save(user);
        if (userCreated != null) {
            return Optional.of(createUserResponseMapper.createUserResponseDto(userCreated));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<UserResponseDto> updateUserById(UserUpdateDto userRequest, UUID id) {
        Set<Role> roles = roleRepository.findAllByRoleIn(Set.of(userRequest.roles()));
        Optional<User> userDb = userRepository.findById(id);
        if (userDb.isPresent()) {
            userDb.get().setUsername(userRequest.username());
            userDb.get().setUpdated(new Date());
            userDb.get().setRoles(roles);
            userDb.get().setEnable(userRequest.enable());
            userDb.get().setChangePass(userRequest.changePass());
            userRepository.save(userDb.get());
            return Optional.of(createUserResponseMapper.createUserResponseDto(userDb.get()));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<User> deleteUserById(UUID id) {
        Optional<User> userDb = userRepository.findById(id);
        userDb.ifPresent(userRepository::delete);
        return userDb;
    }
}
