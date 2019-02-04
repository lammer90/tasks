package ru.plotnikov.example.controller.commands.impl.common;

import ru.plotnikov.example.controller.commands.AbstractCommand;
import ru.plotnikov.example.model.Project;

import java.time.LocalDate;

public abstract class AddUpdateProject extends AbstractCommand<String> {

    @Override
    public String execute() {
        return "id проекта: " + process(read());
    }

    private Project read(){
        controller.printMassage("Введите реквизиты проекта(При редактировании данные, которые не нужно менять можно пропустить, нажав Enter) \n");
        controller.printMassage("Введите наименование проекта:");
        String name = controller.readLine();
        controller.printMassage("Введите описание проекта:");
        String desc = controller.readLine();

        controller.printMassage("Введите дату старта проекта (yyyy-mm-dd):");
        LocalDate date1 = LocalDate.MIN;
        while (date1.equals(LocalDate.MIN)) {
            date1 = controller.verifyDate(controller.readLine(), "Не верный формат даты, попробуйте еще раз:");
        }
        controller.printMassage("Введите дату окончания проекта (yyyy-mm-dd):");
        LocalDate date2 = LocalDate.MIN;
        while (date2.equals(LocalDate.MIN)) {
            date2 = controller.verifyDate(controller.readLine(), "Не верный формат даты, попробуйте еще раз:");
        }
        return new Project(name, desc, date1, date2);
    }

    protected abstract String process(Project project);
}
