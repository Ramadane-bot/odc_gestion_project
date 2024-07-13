package com.odcspring.web_app.controller;

import com.odcspring.web_app.model.Role;
import com.odcspring.web_app.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class RoleController {
   private final RoleRepository roleRepository;

   @RequestMapping("/roles")
   public String role(Model model) {
      model.addAttribute("role", new Role());
      model.addAttribute("roleList", roleRepository.findAll());
      return "roles/roles";
   }

   @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
   public String roleSave(@ModelAttribute Role role, Model model){
      roleRepository.save(role);
      return "redirect:/roles";
   }

}
