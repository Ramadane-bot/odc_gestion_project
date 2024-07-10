package com.odcspring.web_app.controller;

import com.odcspring.web_app.model.AuthRequest;
import com.odcspring.web_app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    @GetMapping
    public String index(Model model){
        model.addAttribute("user", new AuthRequest());
        return "auth/login";
    }

    @PostMapping
    public String auth(@ModelAttribute AuthRequest request){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        authenticationManager.authenticate(authentication);

        return "redirect:/tasks";
    }
}
