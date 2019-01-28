package ru.plotnikov.example.repository;

import ru.plotnikov.example.model.Project;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryProjectRepository {

    private static int ID = 0;

    private Map<Integer, Project> storage = new HashMap<>();

    public int addProject(Project project) {
        int newId = ++ID;
        project.setId(newId);
        storage.put(newId, project);
        return newId;
    }

    public Project updateProject(int id, Project project) {
        if (!storage.containsKey(id)){
            return null;
        }
        project.setId(id);
        return storage.put(id, project);
    }

    public Project getProject(int id) {
        return storage.get(id);
    }

    public List<Project> getAllProject() {
        return storage.values().stream().sorted(Comparator.comparing(Project::getStartDate)).collect(Collectors.toList());
    }

    public boolean deleteProject(int id) {
        return storage.remove(id) != null;
    }
}
