package ru.plotnikov.example.repository;

import ru.plotnikov.example.model.Task;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTaskRepository {

    private static int ID = 0;

    private Map<Integer, Task> storage = new HashMap<>();

    public int addTask(Task task){
        int newId = ID++;
        task.setId(newId);
        storage.put(newId, task);
        return newId;
    }

    public Task updateTask(Task task){
        return storage.put(task.getId(), task);
    }

    public Task getTask(int id){
        return storage.get(id);
    }

    public List<Task> getAllTask(){
        return storage.values().stream().sorted(Comparator.comparing(Task::getCreateDate)).collect(Collectors.toList());
    }

    public List<Task> getAllTaskFilter(int id){
        return storage.values().stream().filter(t -> t.getProject().getId() == id).sorted(Comparator.comparing(Task::getCreateDate)).collect(Collectors.toList());
    }

    public boolean deleteTask(int id){
        return storage.remove(id) != null;
    }
}
