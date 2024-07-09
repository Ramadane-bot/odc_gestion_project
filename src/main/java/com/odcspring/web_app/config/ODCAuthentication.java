package com.odcspring.web_app.config;

import com.odcspring.web_app.model.User;
import com.odcspring.web_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ODCAuthentication implements AuthenticationManager {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        User user = (User) userService.loadUserByUsername(username);
        if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())){
            throw new RuntimeException("Password incorrect");
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                username,
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return authenticated;
    }
}
