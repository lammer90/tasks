package ru.plotnikov.example.view;

import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.model.Task;

import java.util.List;

public class ConsolePrintView {
    
    public void printMassage(String massege){
        System.out.println(massege);
    }
    
    public void printMenu(){
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Показать все проекты с задачами; \n" +
                "2. Показать все проекты; \n" +
                "3. Показать все задачи по проекту; \n" +
                "4. Меню проектов; \n" +
                "5. Меню задач; \n" +
                "6. Выход. \n");
    }

    public void printTaskMenu(){
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Добавить задачу; \n" +
                "2. Обновить задачу; \n" +
                "3. Удалить задачу; \n" +
                "4. Вернуться. \n");
    }

    public void printProjectMenu() {
        System.out.println("Выберите пункт меню для дальнейших действий: \n" +
                "1. Добавить проект; \n" +
                "2. Обновить проект; \n" +
                "3. Удалить проект; \n" +
                "4. Вернуться. \n");
    }

    public void printAllProjectsWithTasks(List<Project> projects, List<Task> tasks){
        projects.forEach(project -> {
            //System.out.println("Проект: {");
            System.out.println(project.toString());
            //System.out.println("          Задача: [");
            tasks.stream().filter(t -> t.getProject().equals(project)).forEach(t -> System.out.println(t.toString()));
            //System.out.println("          ]");
            //System.out.println("}");
        });
    }

    public <T> void printAll(List<T> projects){
        projects.forEach(project -> System.out.println(project.toString()));
    }
}
