package ru.plotnikov.example.controller.commands.impl.common;

import ru.plotnikov.example.controller.commands.AbstractCommand;

public abstract class DeleteObject extends AbstractCommand<String> {

    @Override
    public String execute() {
        return delete(readId());
    }

    protected abstract String delete(int id);

    protected abstract int readId();
}
