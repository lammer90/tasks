package ru.plotnikov.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private int id;

    private String name;
    
    private String description;

    private LocalDate createDate;

    private LocalDateTime deadLine;

    private Project project;

    public Task(String name, String description, LocalDate createDate, LocalDateTime deadLine, Project project) {
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.deadLine = deadLine;
        this.project = project;
    }

    public Task(String name, String description, LocalDate createDate, LocalDateTime deadLine) {
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "          Задача: " +
                "          id=" + id + "\n" +
                "          Наименование ='" + name + '\'' + "\n" +
                "          Описание ='" + description + '\'' + "\n" +
                "          Дата создания =" + createDate + "\n" +
                "          Дэдлайн =" + deadLine + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
