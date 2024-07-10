package com.odcspring.web_app.service;

import com.odcspring.web_app.model.User;
import com.odcspring.web_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public void add(User user){
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        repository.save(user);
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Cet Utilisateur n'existe pas"));
    }
}
