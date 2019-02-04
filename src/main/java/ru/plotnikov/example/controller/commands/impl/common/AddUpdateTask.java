package ru.plotnikov.example.controller.commands.impl.common;

import ru.plotnikov.example.controller.commands.AbstractCommand;
import ru.plotnikov.example.model.Task;

import java.time.LocalDateTime;

public abstract class AddUpdateTask extends AbstractCommand<String> {

    @Override
    public String execute() {
        return "id задачи: " + process(read());
    }

    private Task read() {
        controller.printMassage("Введите реквизиты задачи(При редактировании данные, которые не нужно менять можно пропустить, нажав Enter) \n");
        controller.printMassage("Введите наименование задачи:");
        String name = controller.readLine();
        controller.printMassage("Введите описание задачи:");
        String desc = controller.readLine();

        controller.printMassage("Введите дату и время сдачи (YYYY-MM-DDThh:mm:ss):");
        LocalDateTime date = LocalDateTime.MIN;
        while (date.equals(LocalDateTime.MIN)) {
            date = controller.verifyDateTime(controller.readLine(), "Не верный формат даты, попробуйте еще раз:");
        }
        return new Task(name, desc, date);
    }

    protected abstract String process(Task project);

}
