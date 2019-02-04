package ru.plotnikov.example.controller.commands.impl.common;

import ru.plotnikov.example.controller.commands.AbstractCommand;

import java.util.List;

public abstract class PrintAllObject<T> extends AbstractCommand<String> {

    @Override
    public String execute() {
        return printAll(getAll());
    }

    protected abstract List<T> getAll();

    private String printAll(List<T> objs) {
        StringBuilder builder = new StringBuilder();
        objs.forEach(obj -> {
            builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            builder.append(obj.toString()).append("\n");
            builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        });
        return builder.toString();
    }
}
