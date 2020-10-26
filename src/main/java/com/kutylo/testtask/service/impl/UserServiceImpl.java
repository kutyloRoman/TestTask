package com.kutylo.testtask.service.impl;

import com.kutylo.testtask.dto.request.UserRequest;
import com.kutylo.testtask.dto.response.UserResponse;
import com.kutylo.testtask.mapper.UserMapper;
import com.kutylo.testtask.model.User;
import com.kutylo.testtask.repository.UserRepository;
import com.kutylo.testtask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void initEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by username:" + username));
    }

    @Override
    public void createNewUser(UserRequest userRequest) {
        log.info("Add new user:" + userRequest);

        if (userRepository.findUserByUsername(userRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exist with this username:" + userRequest.getUsername());
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setIsAdmin(true);

        userRepository.save(user);
    }

}
