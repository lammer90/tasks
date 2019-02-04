package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.impl.common.AddUpdateTask;
import ru.plotnikov.example.model.Task;

public class AddTask extends AddUpdateTask {

    @Override
    public String getName() {
        return "task ~ add";
    }

    @Override
    public String getDescription() {
        return "Add new task";
    }

    @Override
    protected String process(Task task) {
        return service.addTask(controller.readProjectId(), task);
    }
}
