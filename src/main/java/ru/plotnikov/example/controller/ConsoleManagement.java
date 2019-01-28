package ru.plotnikov.example.controller;

import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.repository.InMemoryProjectRepository;
import ru.plotnikov.example.repository.InMemoryTaskRepository;
import ru.plotnikov.example.view.ConsolePrintView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleManagement {

    private InMemoryProjectRepository projectRepository;

    private InMemoryTaskRepository taskRepository;

    private ConsolePrintView view;

    private boolean exit = false;

    public ConsoleManagement(InMemoryProjectRepository projectRepository, InMemoryTaskRepository taskRepository, ConsolePrintView view) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.view = view;
    }

    public static void main(String[] args) {
        ConsoleManagement manager = new ConsoleManagement(
                new InMemoryProjectRepository(),
                new InMemoryTaskRepository(),
                new ConsolePrintView(new Scanner(System.in)));

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
                    manager.updateProject();
                    break;
                }
                case 5: {
                    manager.updateTasks();
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

    private void updateProject() {
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
                view.printMassage("Обновленный проект: " + projectRepository.updateProject(oldProject.getId(), updateProjectFields(oldProject)).toString());
                break;
            }
            case 3: {
                int id = view.readProjectId();
                projectRepository.deleteProject(id);
                getAllProjects();
                break;
            }
            default:
                break;
        }
    }

    private Project updateProjectFields(Project oldProject) {
        String name = view.readLine("Введите новое имя проекта или нажмите Enter, если это поле не меняется");
        if (!name.equals("")) {
            oldProject.setName(name);
        }
        String desc = view.readLine("Введите новое описание проекта или нажмите Enter, если это поле не меняется");
        if (!desc.equals("")) {
            oldProject.setDescription(desc);
        }
        LocalDate date1 = verifyDate(view.readLine("Введите новую дату старта проекта или нажмите Enter, если это поле не меняется (yyyy-mm-dd)"), "Не верная дата начала");
        if (!date1.equals(LocalDate.EPOCH)) {
            oldProject.setStartDate(date1);
        }
        LocalDate date2 = verifyDate(view.readLine("Введите новую дату окончания проекта или нажмите Enter, если это поле не меняется (yyyy-mm-dd)"), "Не верная дата окончания");
        if (!date2.equals(LocalDate.EPOCH)) {
            oldProject.setEndDate(date2);
        }
        return oldProject;
    }

    private LocalDate verifyDate(String date, String msg) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            view.printMassage(msg);
            return LocalDate.EPOCH;
        }
    }

    private void updateTasks() {
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
