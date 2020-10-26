package com.kutylo.testtask.service.impl;

import com.kutylo.testtask.dto.request.AuthenticationRequest;
import com.kutylo.testtask.dto.response.AuthenticationResponse;
import com.kutylo.testtask.model.User;
import com.kutylo.testtask.security.jwt.JwtTokenProvider;
import com.kutylo.testtask.service.AuthenticationService;
import com.kutylo.testtask.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@Data
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private BCryptPasswordEncoder encoder;

    @PostConstruct
    private void initEncoder() {
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        log.info("Logging user with name = " + authenticationRequest.getUsername());
        User user = userService.findUserByUsername(authenticationRequest.getUsername());
        if (!encoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            log.error("Password not correct");
            throw new IllegalArgumentException("Password not correct");
        }

        String token = jwtTokenProvider.createToken(user.getUsername(), user.getIsAdmin());
        return new AuthenticationResponse(
                user.getUsername(),
                token
        );
    }
}
