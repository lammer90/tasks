package ru.plotnikov.example.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.plotnikov.example.service.TaskServiceImpl;
import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsolePrintHelper {

    private TaskServiceImpl controller;

    private Scanner scanner;
    private static final Logger logMagenta = LoggerFactory.getLogger("magenta");
    private static final Logger logCyan = LoggerFactory.getLogger("cyan");

    public ConsolePrintHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start(){
        int i = printMenu();
        switch (i) {
            case 1: {
                printAllProjectsWithTasks(controller.getAllProjects(), controller.getAllTasks());
                break;
            }
            case 2: {
                printAll(controller.getAllProjects());
                break;
            }
            case 3: {
                printAll(controller.getAllTasksFilterByProject(readProjectId()));
                break;
            }
            case 4: {
                projectMenu();
                break;
            }
            case 5: {
                taskMenu();
                break;
            }
            case 6: {
                scanner.close();
                break;
            }
            default:
                break;
        }
    }

    private void projectMenu() {
        int i = printProjectMenu();
        switch (i) {
            case 1: {
                printMassage("id нового проекта: " + controller.addProject(readProject()));
                break;
            }
            case 2: {
                printMassage(controller.updateProject(readProjectId(), readProject()));
                break;
            }
            case 3: {
                printMassage(controller.deleteProject(readProjectId()));
                printAll(controller.getAllProjects());
                break;
            }
            default:
                break;
        }
    }

    private void taskMenu() {
        int i = printTaskMenu();
        switch (i) {
            case 1: {
                printMassage(controller.addTask(readProjectId(), readTask()));
                break;
            }
            case 2: {
                printMassage(controller.updateTask(readTaskId(), readTask()));
                break;
            }
            case 3: {
                printMassage(controller.deleteTask(readTaskId()));
                break;
            }
            default:
                break;
        }
    }

    private int readInt() {
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    private void printMassage(String massege) {
        System.out.println(massege + "\n");
    }

    private int printMenu() {
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Показать все проекты с задачами; \n" +
                "2. Показать все проекты; \n" +
                "3. Показать все задачи по проекту; \n" +
                "4. Меню проектов; \n" +
                "5. Меню задач; \n" +
                "6. Выход. \n");
        return readInt();
    }

    private int printTaskMenu() {
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Добавить задачу; \n" +
                "2. Обновить задачу; \n" +
                "3. Удалить задачу; \n" +
                "4. Вернуться. \n");
        return readInt();
    }

    private int printProjectMenu() {
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Добавить проект; \n" +
                "2. Обновить проект; \n" +
                "3. Удалить проект; \n" +
                "4. Вернуться. \n");
        return readInt();
    }

    private int readProjectId() {
        printMassage("Введите id проекта:");
        return readInt();
    }

    private int readTaskId() {
        printMassage("Введите id задачи:");
        return readInt();
    }

    private Project readProject() {
        printMassage("Введите реквизиты проекта(При редактировании данные, которые не нужно менять можно пропустить, нажав Enter) \n");
        printMassage("Введите наименование проекта:");
        String name = scanner.nextLine();
        printMassage("Введите описание проекта:");
        String desc = scanner.nextLine();

        printMassage("Введите дату старта проекта (yyyy-mm-dd):");
        LocalDate date1 = LocalDate.MIN;
        while (date1.equals(LocalDate.MIN)) {
            date1 = verifyDate(scanner.nextLine(), "Не верный формат даты, попробуйте еще раз:");
        }
        printMassage("Введите дату окончания проекта (yyyy-mm-dd):");
        LocalDate date2 = LocalDate.MIN;
        while (date2.equals(LocalDate.MIN)) {
            date2 = verifyDate(scanner.nextLine(), "Не верный формат даты, попробуйте еще раз:");
        }
        return new Project(name, desc, date1, date2);
    }

    private Task readTask() {
        printMassage("Введите реквизиты задачи(При редактировании данные, которые не нужно менять можно пропустить, нажав Enter) \n");
        printMassage("Введите наименование задачи:");
        String name = scanner.nextLine();
        printMassage("Введите описание задачи:");
        String desc = scanner.nextLine();

        printMassage("Введите дату и время сдачи (YYYY-MM-DDThh:mm:ss):");
        LocalDateTime date = LocalDateTime.MIN;
        while (date.equals(LocalDateTime.MIN)) {
            date = verifyDateTime(scanner.nextLine(), "Не верный формат даты, попробуйте еще раз:");
        }
        return new Task(name, desc, date);
    }

    private LocalDate verifyDate(String date, String msg) {
        if (date.equals("")) {
            return LocalDate.MAX;
        }
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            printMassage(msg);
            return LocalDate.MIN;
        }
    }

    private LocalDateTime verifyDateTime(String date, String msg) {
        if (date.equals("")) {
            return LocalDateTime.MAX;
        }
        try {
            return LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            printMassage(msg);
            return LocalDateTime.MIN;
        }
    }

    private void printAllProjectsWithTasks(List<Project> projects, List<Task> tasks) {
        projects.forEach(project -> {
            logMagenta.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logMagenta.info(project.toString());
            tasks.stream().filter(t -> project.equals(t.getProject())).forEach(t -> logCyan.info(t.toString()));
            logMagenta.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        });
    }

    private  <T> void printAll(List<T> obj) {
        obj.forEach(project -> {
            logMagenta.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logMagenta.info(project.toString() + "\n");
            logMagenta.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        });
    }

    public void setController(TaskServiceImpl controller) {
        this.controller = controller;
    }
}
