package com.odcspring.web_app.config;

import com.odcspring.web_app.model.User;
import com.odcspring.web_app.repository.UserRepository;
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
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = (User) userService.loadUserByUsername(authentication.getPrincipal().toString());
        String password = authentication.getCredentials().toString();
        User user1 = userRepository.findByEmail(authentication.getPrincipal().toString()).orElse(null);
        String v = String.valueOf(user1.getRoles().get(0));
        System.out.println("********************* Authentication ******************\n"+v);

        if (!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("Le mot de passe n'est pas valide");
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(v))
        );

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return authenticated;
    }
}
