package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.impl.common.AddUpdateTask;
import ru.plotnikov.example.model.Task;

public class UpdateTask extends AddUpdateTask {

    @Override
    public String getName() {
        return "task ~ update";
    }

    @Override
    public String getDescription() {
        return "Update task";
    }

    @Override
    protected String process(Task task) {
        return service.updateTask(controller.readTaskId(), task);
    }
}
