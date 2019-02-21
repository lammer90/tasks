package ru.plotnikov.example.controller;

import ru.plotnikov.example.controller.commands.Command;
import ru.plotnikov.example.service.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsolePrintHelper {

    private Service service;

    private Scanner scanner;

    private Map<String, Command> commandMap;

    {
        commandMap = new HashMap<>();
    }

    public ConsolePrintHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        String strCommand = scanner.nextLine();
        if (strCommand.equals("Exit")) {
            service.setExit();
        } else if (commandMap.containsKey(strCommand)) {
            commandMap.get(strCommand).execute();
        }
    }

    private int readInt() {
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printMassage(String massage) {
        System.out.println(massage + "\n");
    }

    public int readProjectId() {
        printMassage("Введите id проекта:");
        return readInt();
    }

    public int readTaskId() {
        printMassage("Введите id задачи:");
        return readInt();
    }

    public LocalDate verifyDate(String date, String msg) {
        if (date.equals("")) {
            return LocalDate.MAX;
        }
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            printMassage(msg);
            return LocalDate.MIN;
        }
    }

    public LocalDateTime verifyDateTime(String date, String msg) {
        if (date.equals("")) {
            return LocalDateTime.MAX;
        }
        try {
            return LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            printMassage(msg);
            return LocalDateTime.MIN;
        }
    }

    public void setService(Service service) {
        this.service = service;
    }
}
