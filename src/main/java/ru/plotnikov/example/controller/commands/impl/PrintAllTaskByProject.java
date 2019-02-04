package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.impl.common.PrintAllObject;
import ru.plotnikov.example.model.Task;

import java.util.List;

public class PrintAllTaskByProject extends PrintAllObject<Task> {
    @Override
    public String getName() {
        return "task ~ printAllByProject";
    }

    @Override
    public String getDescription() {
        return "Print all task by project";
    }

    @Override
    protected List<Task> getAll() {
        return service.getAllTasksFilterByProject(controller.readProjectId());
    }
}
