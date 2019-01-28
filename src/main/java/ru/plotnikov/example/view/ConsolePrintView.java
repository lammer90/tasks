package ru.plotnikov.example.view;

public class ConsolePrintView {
    
    public void printMassage(String massege){
        System.out.println(massege);
    }
    
    public void printMenu(){
        System.out.println("Выберите пункт меню для дальнейших действий: " +
                "1. Показать все проекты с задачами; " +
                "2. Показать все проекты;" +
                "3. Показать все задачи по проекту;" +
                "4. Редактировать/Добавить проект;" + 
                "5. Редактировать/Добавить задачу;" + 
                "6. Выход.");
    }
}
