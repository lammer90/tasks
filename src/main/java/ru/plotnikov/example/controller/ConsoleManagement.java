package ru.plotnikov.example.controller;

import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.repository.InMemoryProjectRepository;
import ru.plotnikov.example.repository.InMemoryTaskRepository;
import ru.plotnikov.example.view.ConsolePrintView;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleManagement {

    private InMemoryProjectRepository projectRepository;

    private InMemoryTaskRepository taskRepository;

    private ConsolePrintView view;

    private static Scanner scanner;

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
                new ConsolePrintView());

        scanner = new Scanner(System.in);
        while (!manager.exit) {
            manager.view.printMenu();
            int i = scanner.nextInt();
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
                    manager.updateProject(manager);
                    break;
                }
                case 5: {
                    manager.updateTasks(manager);
                    break;
                }
                case 6: {
                    manager.setExit();
                    scanner.close();
                    break;
                }
                default:
                    break;
            }
        }

    }

    private void updateProject(ConsoleManagement manager) {
        view.printProjectMenu();
        int i = scanner.nextInt();
        switch (i) {
            case 1: {
                scanner.nextLine();
                view.printMassage("Введите реквизиты нового проекта");
                view.printMassage("id нового проекта: " + projectRepository.addProject(readProject()));
                break;
            }
            case 2: {
                scanner.nextLine();
                view.printMassage("Введите id редактируемого проекта:");
                int id = scanner.nextInt();
                scanner.nextLine();
                view.printMassage("Введите новые реквизиты проекта, если какой-то реквизит не меняется - нажмите Enter:");
                Project project = readProject();
                view.printMassage("Обновленный проект: " + projectRepository.updateProject(id, project).toString());
            }
            case 3: {
                scanner.nextLine();
                view.printMassage("Введите id удаляемого проекта:");
                int id = scanner.nextInt();
                projectRepository.deleteProject(id);
                getAllProjects();
                break;
            }
            case 4: {
                break;
            }
            default:
                break;
        }
    }

    private void updateTasks(ConsoleManagement manager) {
    }

    private void getAllProjectsWithTasks() {
        view.printAllProjectsWithTasks(projectRepository.getAllProject(), taskRepository.getAllTask());
    }

    private void getAllProjects() {
        view.printAll(projectRepository.getAllProject());
    }

    private void getAllTasksFilterByProject() {
        view.printMassage("Введите id проекта для которого хотите получить задачи:");
        view.printAll(taskRepository.getAllTaskFilter(scanner.nextInt()));
    }

    private void setExit() {
        this.exit = true;
    }

    private Project readProject(){
        view.printMassage("Введите наименование проекта:");
        String name = scanner.nextLine();
        view.printMassage("Введите описание проекта:");
        String desc = scanner.nextLine();
        view.printMassage("Введите дату старта проекта (yyyy-mm-dd):");
        LocalDate date1 = LocalDate.parse(scanner.nextLine());
        view.printMassage("Введите дату окончания проекта (yyyy-mm-dd):");
        LocalDate date2 = LocalDate.parse(scanner.nextLine());
        return new Project(name, desc, date1, date2);
    }
}
