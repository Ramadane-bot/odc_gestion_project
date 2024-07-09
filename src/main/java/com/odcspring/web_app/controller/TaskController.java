package com.odcspring.web_app.controller;

import com.odcspring.web_app.model.Task;
import com.odcspring.web_app.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;
    @RequestMapping("/tasks")
    public String index(Model model){
        model.addAttribute("task", new Task());

        List<Task> tasks = service.getAll();
        model.addAttribute("taskList", tasks);
        return "tasks/tasks";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Task task, Model model){
        System.out.println("******* Ajout d'une tache *******");
        System.out.println(task);

        service.add(task);
        model.addAttribute("taks", task);
        return "redirect:/tasks";
    }
}
