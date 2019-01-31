package ru.plotnikov.example.repository;

import org.springframework.stereotype.Repository;
import ru.plotnikov.example.model.Task;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InMemoryTaskRepository implements TaskRepository{

    private static int ID = 0;

    private Map<Integer, Task> storage = new HashMap<>();

    public InMemoryTaskRepository() {
    }

    public int addTask(Task task){
        int newId = ++ID;
        task.setId(newId);
        storage.put(newId, task);
        return newId;
    }

    public Task updateTask(int id, Task task){
        if (!storage.containsKey(id)){
            return null;
        }
        task.setId(id);
        return storage.put(id, task);
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
