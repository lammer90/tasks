package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.impl.common.AddUpdateProject;
import ru.plotnikov.example.model.Project;

public class AddProject extends AddUpdateProject {

    @Override
    public String getName() {
        return "project ~ add";
    }

    @Override
    public String getDescription() {
        return "Add new project";
    }

    @Override
    protected String process(Project project) {
        return service.addProject(project);
    }
}
