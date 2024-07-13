package com.odcspring.web_app.controller;

import com.odcspring.web_app.model.User;
import com.odcspring.web_app.repository.RoleRepository;
import com.odcspring.web_app.repository.UserRepository;
import com.odcspring.web_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @RequestMapping("/utilisateurs")
    public String utilisateurs(Model model) {

        model.addAttribute("newUtilisateur", new User());
        List<User> utli= userRepository.findAll();
        model.addAttribute("utilisateurs", utli);
        model.addAttribute("roles", roleRepository.findAll());
        return "utilisateur/utilisateurHome";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User utilisateur, Model model) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        userRepository.save(utilisateur);

        return  "redirect:/utilisateurs";
    }

    @GetMapping("/utilisateurs/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/utilisateurs";
    }

    @GetMapping("/utilisateurs/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {
        Optional<User> utilisateur = userRepository.findById(id);
        model.addAttribute("utilisateur", utilisateur.get());
        return "utilisateur/editUtilisateur";
    }

    @PostMapping("/utilisateurs/update/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User utilisateur, Model model) {
        utilisateur.setId(id);
        userRepository.save(utilisateur);
        return "redirect:/utilisateurs";
    }
}
