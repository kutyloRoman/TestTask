package com.kutylo.testtask.security;

import com.kutylo.testtask.model.User;
import com.kutylo.testtask.security.jwt.JwtUserFactory;
import com.kutylo.testtask.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Data
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by name = " + username);
        User user = userService.findUserByUsername(username);
        return JwtUserFactory.create(user);
    }
}
