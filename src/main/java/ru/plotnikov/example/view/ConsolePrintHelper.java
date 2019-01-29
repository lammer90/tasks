package ru.plotnikov.example.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsolePrintHelper {

    private Scanner scanner;
    private static final Logger logMagenta = LoggerFactory.getLogger("magenta");
    private static final Logger logCyan = LoggerFactory.getLogger("cyan");

    public ConsolePrintHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt() {
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void printMassage(String massege) {
        System.out.println(massege + "\n");
    }

    public int printMenu() {
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Показать все проекты с задачами; \n" +
                "2. Показать все проекты; \n" +
                "3. Показать все задачи по проекту; \n" +
                "4. Меню проектов; \n" +
                "5. Меню задач; \n" +
                "6. Выход. \n");
        return readInt();
    }

    public int printTaskMenu() {
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Добавить задачу; \n" +
                "2. Обновить задачу; \n" +
                "3. Удалить задачу; \n" +
                "4. Вернуться. \n");
        return readInt();
    }

    public int printProjectMenu() {
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Добавить проект; \n" +
                "2. Обновить проект; \n" +
                "3. Удалить проект; \n" +
                "4. Вернуться. \n");
        return readInt();
    }

    public int readProjectId() {
        printMassage("Введите id проекта:");
        return readInt();
    }

    public int readTaskId() {
        printMassage("Введите id задачи:");
        return readInt();
    }

    public Project readProject() {
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

    public Task readTask() {
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

    public void printAllProjectsWithTasks(List<Project> projects, List<Task> tasks) {
        projects.forEach(project -> {
            logMagenta.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logMagenta.info(project.toString());
            tasks.stream().filter(t -> project.equals(t.getProject())).forEach(t -> logCyan.info(t.toString()));
            logMagenta.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        });
    }

    public <T> void printAll(List<T> projects) {
        projects.forEach(project -> {
            logMagenta.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logMagenta.info(project.toString() + "\n");
            logMagenta.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        });
    }
}
