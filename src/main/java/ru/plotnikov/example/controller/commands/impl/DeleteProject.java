package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.impl.common.DeleteObject;

public class DeleteProject extends DeleteObject {

    @Override
    public String getName() {
        return "project ~ delete";
    }

    @Override
    public String getDescription() {
        return "Delete project";
    }

    @Override
    protected String delete(int id) {
        return service.deleteProject(id);
    }

    @Override
    protected int readId() {
        return controller.readProjectId();
    }
}
