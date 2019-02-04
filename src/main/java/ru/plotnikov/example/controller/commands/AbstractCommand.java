package ru.plotnikov.example.controller.commands;

import ru.plotnikov.example.controller.ConsolePrintHelper;
import ru.plotnikov.example.service.Service;

public abstract class AbstractCommand<T> implements Command<T> {
    protected Service service;

    protected ConsolePrintHelper controller;

    public void setService(Service service) {
        this.service = service;
    }

    public void setController(ConsolePrintHelper controller) {
        this.controller = controller;
    }
}
