package ru.plotnikov.example.view;

import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsolePrintView {

    private Scanner scanner;

    public ConsolePrintView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(){
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

    public int readProjectId(){
        printMassage("Введите id проекта:");
        return readInt();
    }

    public String readLine(String massage){
        printMassage(massage);
        return scanner.nextLine();
    }

    public Project readProject(){
        printMassage("Введите наименование проекта:");
        String name = scanner.nextLine();
        printMassage("Введите описание проекта:");
        String desc = scanner.nextLine();
        printMassage("Введите дату старта проекта (yyyy-mm-dd):");
        LocalDate date1 = LocalDate.parse(scanner.nextLine());
        printMassage("Введите дату окончания проекта (yyyy-mm-dd):");
        LocalDate date2 = LocalDate.parse(scanner.nextLine());
        return new Project(name, desc, date1, date2);
    }

    public void printAllProjectsWithTasks(List<Project> projects, List<Task> tasks) {
        projects.forEach(project -> {
            System.out.println(project.toString() + "\n");
            tasks.stream().filter(t -> t.getProject().equals(project)).forEach(t -> System.out.println(t.toString() + "\n"));

        });
    }

    public <T> void printAll(List<T> projects) {
        projects.forEach(project -> System.out.println(project.toString() + "\n"));
    }
}
