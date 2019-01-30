package ru.plotnikov.example;

import ru.plotnikov.example.controller.Controller;
import ru.plotnikov.example.repository.InMemoryProjectRepository;
import ru.plotnikov.example.repository.InMemoryTaskRepository;
import ru.plotnikov.example.view.ConsolePrintHelper;

import java.util.Scanner;

public class ConsoleManagement {

    public static void main(String[] args) {
        ConsolePrintHelper view = new ConsolePrintHelper(new Scanner(System.in));
        Controller controller = new Controller(
                new InMemoryProjectRepository(),
                new InMemoryTaskRepository(),
                view);
        view.setController(controller);

        while (!controller.isExit()) {
            view.start();
        }

    }
}
