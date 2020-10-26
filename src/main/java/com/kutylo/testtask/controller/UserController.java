package com.kutylo.testtask.controller;

import com.kutylo.testtask.dto.request.AuthenticationRequest;
import com.kutylo.testtask.dto.request.UserRequest;
import com.kutylo.testtask.dto.response.AuthenticationResponse;
import com.kutylo.testtask.repository.UserRepository;
import com.kutylo.testtask.service.AuthenticationService;
import com.kutylo.testtask.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication/")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final UserService userService;

    @ApiOperation(value = "Login user")
    @PostMapping("login")
    public AuthenticationResponse login(@Validated @RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }

    @ApiOperation(value = "Register new user")
    @PostMapping("register")
    public void registration(@Validated @RequestBody UserRequest userRequest) {
        userService.createNewUser(userRequest);
    }


}
