package com.odcspring.web_app.controller;

import com.odcspring.web_app.model.Task;
import com.odcspring.web_app.model.User;
import com.odcspring.web_app.repository.TaskRepository;
import com.odcspring.web_app.repository.UserRepository;
import com.odcspring.web_app.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

}
