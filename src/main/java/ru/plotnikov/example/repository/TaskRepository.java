package ru.plotnikov.example.repository;

import ru.plotnikov.example.model.Task;

import java.util.List;

public interface TaskRepository {

    int addTask(Task task);

    Task updateTask(int id, Task task);

    Task getTask(int id);

    List<Task> getAllTask();

    List<Task> getAllTaskFilter(int id);

    boolean deleteTask(int id);
}
