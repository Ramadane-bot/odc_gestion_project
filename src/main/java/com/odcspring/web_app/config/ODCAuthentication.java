package com.odcspring.web_app.config;

import com.odcspring.web_app.model.User;
import com.odcspring.web_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ODCAuthentication implements AuthenticationManager {
    private final UserService userService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = (User) userService.loadUserByUsername(authentication.getPrincipal().toString());
        String password = authentication.getCredentials().toString();

        if (!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("Le mot de passe n'est pas valide");
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return authenticated;
    }
}
