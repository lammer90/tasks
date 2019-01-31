package ru.plotnikov.example.repository;

import ru.plotnikov.example.model.Project;

import java.util.List;

public interface ProjectRepository {

    int addProject(Project project);

    Project updateProject(int id, Project project);

    Project getProject(int id);

    List<Project> getAllProject();

    boolean deleteProject(int id);
}
