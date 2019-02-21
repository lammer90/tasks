package ru.plotnikov.example;

import ru.plotnikov.example.service.Service;
import ru.plotnikov.example.repository.InMemoryProjectRepository;
import ru.plotnikov.example.repository.InMemoryTaskRepository;
import ru.plotnikov.example.controller.ConsolePrintHelper;

import java.util.Scanner;

public class ConsoleManagement {

    public static void main(String[] args) {
        ConsolePrintHelper view = new ConsolePrintHelper(new Scanner(System.in));
        Service service = new Service(
                new InMemoryProjectRepository(),
                new InMemoryTaskRepository(),
                view);
        view.setService(service);

        while (!service.isExit()) {
            view.start();
        }

    }
}
