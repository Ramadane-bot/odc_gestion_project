package com.odcspring.web_app.controller;

import com.odcspring.web_app.model.User;
import com.odcspring.web_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/add-user")
    public String add(@ModelAttribute User user){
        service.add(user);
        return "";
    }
}
