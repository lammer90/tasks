package ru.plotnikov.example.repository;

import org.springframework.stereotype.Repository;
import ru.plotnikov.example.model.Project;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InMemoryProjectRepository implements ProjectRepository{

    private static int ID = 0;

    private Map<Integer, Project> storage = new HashMap<>();

    public InMemoryProjectRepository() {
        storage.put(10, new Project("rgregreg", "efuhrueghfreg", LocalDate.parse("2012-01-01"), LocalDate.parse("2013-01-01")));
    }

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
