package com.odcspring.web_app.controller;

import com.odcspring.web_app.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController {

    @RequestMapping("/tasks")
    public String index(Model model){
        model.addAttribute("task", new Task());
        return "tasks/tasks";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(){

        return "";
    }
}
