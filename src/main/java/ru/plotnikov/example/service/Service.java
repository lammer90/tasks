package ru.plotnikov.example.service;

import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.model.Task;
import ru.plotnikov.example.repository.InMemoryProjectRepository;
import ru.plotnikov.example.repository.InMemoryTaskRepository;
import ru.plotnikov.example.controller.ConsolePrintHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Service {

    private InMemoryProjectRepository projectRepository;

    private InMemoryTaskRepository taskRepository;

    private ConsolePrintHelper view;

    private boolean exit = false;

    public Service(InMemoryProjectRepository projectRepository, InMemoryTaskRepository taskRepository, ConsolePrintHelper view) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.view = view;
    }

    public String addProject(Project project) {
        return String.valueOf(projectRepository.addProject(project));
    }

    public String updateProject(int id, Project project) {
        Project oldProject = projectRepository.getProject(id);
        if (oldProject == null) {
            return "id проекта не существует!!!";
        }
        return "Обновленный проект: " + projectRepository.updateProject(oldProject.getId(), updateProjectFields(oldProject, project)).toString();
    }

    public String deleteProject(int id) {
        if (!projectRepository.deleteProject(id)) {
            return "id проекта не существует!!!";
        }
        return "Проект успешно удален";
    }

    public String addTask(int projId, Task task) {
        Project project = projectRepository.getProject(projId);
        if (project == null) {
            return "id проекта не существует!!!";
        }
        int id = taskRepository.addTask(task.setProject(project));
        return "id новой задачи: " + id;
    }

    public String updateTask(int id, Task task) {
        Task oldTask = taskRepository.getTask(id);
        if (oldTask == null) {
            return "id задачи не существует!!!";
        }
        return "Обновленная задача: " + taskRepository.updateTask(oldTask.getId(), updateTaskFields(oldTask, task)).toString();
    }

    public String deleteTask(int id) {
        if (!taskRepository.deleteTask(id)) {
            return "id не существует!!!";
        }
        return "Задача успешно удалена";
    }

    private Task updateTaskFields(Task oldTask, Task newTask) {
        String name = newTask.getName();
        if (!name.equals("")) {
            oldTask.setName(name);
        }
        String desc = newTask.getDescription();
        if (!desc.equals("")) {
            oldTask.setDescription(desc);
        }
        LocalDateTime date = newTask.getDeadLine();
        if (!date.equals(LocalDateTime.MAX)) {
            oldTask.setDeadLine(date);
        }
        return oldTask;
    }

    private Project updateProjectFields(Project oldProject, Project newProject) {
        String name = newProject.getName();
        if (!name.equals("")) {
            oldProject.setName(name);
        }
        String desc = newProject.getDescription();
        if (!desc.equals("")) {
            oldProject.setDescription(desc);
        }
        LocalDate date1 = newProject.getStartDate();
        if (!date1.equals(LocalDate.MAX)) {
            oldProject.setStartDate(date1);
        }
        LocalDate date2 = newProject.getEndDate();
        if (!date2.equals(LocalDate.MAX)) {
            oldProject.setEndDate(date2);
        }
        return oldProject;
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllProject();
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTask();
    }

    public List<Task> getAllTasksFilterByProject(int id) {
        return taskRepository.getAllTaskFilter(id);
    }

    public void setExit() {
        this.exit = true;
    }

    public boolean isExit() {
        return exit;
    }
}
