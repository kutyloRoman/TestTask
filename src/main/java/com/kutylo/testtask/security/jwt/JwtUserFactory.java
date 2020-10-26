package com.kutylo.testtask.security.jwt;


import com.kutylo.testtask.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getIsAdmin())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(boolean isAdmin) {
        SimpleGrantedAuthority authority;
        if (isAdmin) {
            authority = new SimpleGrantedAuthority("ADMIN");
        } else {
            authority = new SimpleGrantedAuthority("USER");
        }
        return Collections.singletonList(authority);
    }

}
