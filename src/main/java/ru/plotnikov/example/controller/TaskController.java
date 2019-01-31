package ru.plotnikov.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.service.TaskService;

import java.util.List;

@RestController
@RequestMapping(value = "/manager")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Project> getAllProjects() {
        return taskService.getAllProjects();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createWithLocation(@RequestBody Project project) {
        return taskService.addProject(project);
    }

}
