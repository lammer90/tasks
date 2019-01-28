package ru.plotnikov.example.controller;

import ru.plotnikov.example.repository.InMemoryProjectRepository;
import ru.plotnikov.example.repository.InMemoryTaskRepository;
import ru.plotnikov.example.view.ConsolePrintView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ConsoleManagement {

    private InMemoryProjectRepository projectRepository;

    private InMemoryTaskRepository taskRepository;

    private ConsolePrintView view;

    public ConsoleManagement(InMemoryProjectRepository projectRepository, InMemoryTaskRepository taskRepository, ConsolePrintView view) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.view = view;
    }

    public static void main(String[] args) {
        ConsoleManagement manager = new ConsoleManagement(
                new InMemoryProjectRepository(),
                new InMemoryTaskRepository(),
                new ConsolePrintView());

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")))) {

            while (!bufferedReader.readLine().equals("6")) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
