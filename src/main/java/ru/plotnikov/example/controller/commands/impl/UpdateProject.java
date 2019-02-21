package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.impl.common.AddUpdateProject;
import ru.plotnikov.example.model.Project;

public class UpdateProject extends AddUpdateProject {

    @Override
    public String getName() {
        return "project ~ update";
    }

    @Override
    public String getDescription() {
        return "Update project";
    }

    @Override
    protected String process(Project project) {
        return service.updateProject(controller.readProjectId(), project);
    }
}
