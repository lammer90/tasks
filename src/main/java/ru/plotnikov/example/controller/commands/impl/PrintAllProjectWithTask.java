package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.AbstractCommand;
import ru.plotnikov.example.model.Project;
import ru.plotnikov.example.model.Task;

import java.util.List;

public class PrintAllProjectWithTask extends AbstractCommand<String> {

    @Override
    public String getName() {
        return "project ~ printAllWithTask";
    }

    @Override
    public String getDescription() {
        return "All project with tasks";
    }

    @Override
    public String execute() {
        return printAllProjectsWithTasks(service.getAllProjects(), service.getAllTasks());
    }

    private String printAllProjectsWithTasks(List<Project> projects, List<Task> tasks) {
        StringBuilder builder = new StringBuilder();
        projects.forEach(project -> {
            builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            builder.append(project.toString());
            tasks.stream().filter(t -> project.equals(t.getProject())).forEach(t -> builder.append(t.toString()));
            builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        });
        return builder.toString();
    }
}
