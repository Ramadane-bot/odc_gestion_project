package com.odcspring.web_app.service;


import com.odcspring.web_app.model.Task;
import com.odcspring.web_app.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public void add(Task task){
        repository.save(task);
    }

    public List<Task> getAll(){
        return repository.findAll();
    }
}
