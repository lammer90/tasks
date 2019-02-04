package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.impl.common.DeleteObject;

public class DeleteTask extends DeleteObject {

    @Override
    public String getName() {
        return "task ~ delete";
    }

    @Override
    public String getDescription() {
        return "Delete tesk";
    }

    @Override
    protected String delete(int id) {
        return service.deleteTask(id);
    }

    @Override
    protected int readId() {
        return controller.readTaskId();
    }
}
