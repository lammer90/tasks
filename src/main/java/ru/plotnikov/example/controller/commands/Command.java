package ru.plotnikov.example.controller.commands;

public interface Command<T> {
    String getName();

    String getDescription();

    T execute();
}
