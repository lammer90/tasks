package ru.plotnikov.example.service;

import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.model.Task;

import java.util.List;

public interface TaskService {
    String addProject(Project project);

    String updateProject(int id, Project project);

    String deleteProject(int id);

    String addTask(int projId, Task task);

    String updateTask(int id, Task task);

    String deleteTask(int id);

    List<Project> getAllProjects();

    List<Task> getAllTasks();

    List<Task> getAllTasksFilterByProject(int id);
}
