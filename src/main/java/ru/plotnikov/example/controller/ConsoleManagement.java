package ru.plotnikov.example.controller;

import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.model.Task;
import ru.plotnikov.example.repository.InMemoryProjectRepository;
import ru.plotnikov.example.repository.InMemoryTaskRepository;
import ru.plotnikov.example.view.ConsolePrintHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleManagement {

    private InMemoryProjectRepository projectRepository;

    private InMemoryTaskRepository taskRepository;

    private ConsolePrintHelper view;

    private boolean exit = false;

    public ConsoleManagement(InMemoryProjectRepository projectRepository, InMemoryTaskRepository taskRepository, ConsolePrintHelper view) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.view = view;
    }

    public static void main(String[] args) {
        ConsoleManagement manager = new ConsoleManagement(
                new InMemoryProjectRepository(),
                new InMemoryTaskRepository(),
                new ConsolePrintHelper(new Scanner(System.in)));

        while (!manager.exit) {
            int i = manager.view.printMenu();
            switch (i) {
                case 1: {
                    manager.getAllProjectsWithTasks();
                    break;
                }
                case 2: {
                    manager.getAllProjects();
                    break;
                }
                case 3: {
                    manager.getAllTasksFilterByProject();
                    break;
                }
                case 4: {
                    manager.projectMenu();
                    break;
                }
                case 5: {
                    manager.taskMenu();
                    break;
                }
                case 6: {
                    manager.setExit();
                    manager.view.getScanner().close();
                    break;
                }
                default:
                    break;
            }
        }

    }

    private void projectMenu() {
        int i = view.printProjectMenu();
        switch (i) {
            case 1: {
                int id = projectRepository.addProject(view.readProject());
                view.printMassage("id нового проекта: " + id);
                break;
            }
            case 2: {
                Project oldProject = projectRepository.getProject(view.readProjectId());
                if (oldProject == null) {
                    view.printMassage("id не существует!!!");
                    break;
                }
                view.printMassage("Обновленный проект: " + projectRepository.updateProject(oldProject.getId(), updateProjectFields(oldProject, view.readProject())).toString());
                break;
            }
            case 3: {
                int id = view.readProjectId();
                if (!projectRepository.deleteProject(id)) {
                    view.printMassage("id не существует!!!");
                }
                getAllProjects();
                break;
            }
            default:
                break;
        }
    }

    private void taskMenu() {
        int i = view.printTaskMenu();
        switch (i) {
            case 1: {
                Project project = projectRepository.getProject(view.readProjectId());
                if (project == null) {
                    view.printMassage("id не существует!!!");
                    break;
                }
                int id = taskRepository.addTask(view.readTask().setProject(project));
                view.printMassage("id новой задачи: " + id);
                break;
            }
            case 2: {
                Task oldTask = taskRepository.getTask(view.readTaskId());
                if (oldTask == null) {
                    view.printMassage("id не существует!!!");
                    break;
                }
                view.printMassage("Обновленная задача: " + taskRepository.updateTask(oldTask.getId(), updateTaskFields(oldTask, view.readTask())).toString());
                break;
            }
            case 3: {
                int id = view.readTaskId();
                if (!taskRepository.deleteTask(id)) {
                    view.printMassage("id не существует!!!");
                }
                break;
            }
            default:
                break;
        }
    }

    private Task updateTaskFields(Task oldTask, Task newTaskt) {
        String name = newTaskt.getName();
        if (!name.equals("")) {
            oldTask.setName(name);
        }
        String desc = newTaskt.getDescription();
        if (!desc.equals("")) {
            oldTask.setDescription(desc);
        }
        LocalDateTime date = newTaskt.getDeadLine();
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

    private void getAllProjectsWithTasks() {
        view.printAllProjectsWithTasks(projectRepository.getAllProject(), taskRepository.getAllTask());
    }

    private void getAllProjects() {
        view.printAll(projectRepository.getAllProject());
    }

    private void getAllTasksFilterByProject() {
        view.printAll(taskRepository.getAllTaskFilter(view.readProjectId()));
    }

    private void setExit() {
        this.exit = true;
    }
}
