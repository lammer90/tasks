package ru.plotnikov.example.controller.commands.impl;

import ru.plotnikov.example.controller.commands.impl.common.PrintAllObject;
import ru.plotnikov.example.model.Project;

import java.util.List;

public class PrintAllProject extends PrintAllObject<Project> {

    @Override
    public String getName() {
        return "project ~ printAll";
    }

    @Override
    public String getDescription() {
        return "Print all projects";
    }

    @Override
    protected List<Project> getAll() {
        return service.getAllProjects();
    }
}
