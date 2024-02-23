import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    private final HashMap<Integer, Task> TASK_STORAGE = new HashMap<>();

    public HashMap<Integer, Task> getTASK_STORAGE() {
        return TASK_STORAGE;
    }

    public int createANewTask(String enteredName, String enteredDescription) {
        Task createdTask = new Task(enteredName, enteredDescription);
        TASK_STORAGE.put(createdTask.getNUMBER_ID(), createdTask);
        return createdTask.getNUMBER_ID();
    }

    public void changeTask(int enteredId, String enteredName, String enteredDescription) {
        Task taskInEditMode = TASK_STORAGE.get(enteredId);
        if (enteredName.isBlank()) {
            System.out.println("Оставлено прежнее название задачи " + taskInEditMode.getTaskName());
        } else {
            taskInEditMode.setTaskName(enteredName);
            System.out.println("Новое название задачи: " + enteredName);
        }
        if (enteredDescription.isBlank()) {
            System.out.println("Оставлено прежнее описание задачи: " + taskInEditMode.getDescription());
        } else {
            taskInEditMode.setDescription(enteredDescription);
            System.out.println("Новое описание задачи: " + enteredDescription);
        }
    }
    public void recordTaskActions(Scanner scanner, int enteredId) {
        Task taskInEditMode = TASK_STORAGE.get(enteredId);
        while (true) {
            System.out.println("Выберете и введите номер пункта меню:");
            System.out.println("1 - ввести действия для выполнения данной задачи (subTask);");
            System.out.println("2 - завершить ввод действий для выполнения задачи;");
            int actionNumberForSubTask = scanner.nextInt();
            scanner.nextLine();

            switch (actionNumberForSubTask) {
                case 1:
                    System.out.println("Введите название действия (subTask)");
                    String enteredSubTaskName = scanner.nextLine();
                    System.out.println("Введите описание действия (subTask)");
                    String enteredSubTaskDescription = scanner.nextLine();
                    System.out.println(taskInEditMode.addNewSubTask(enteredSubTaskName, enteredSubTaskDescription));
                    System.out.println("***");
                    break;
                case 2:
                    System.out.println("Ввод действий завершён;");
                    System.out.println("***");
                    return;
                default:
                    System.out.println("Такого пункта меню нет, попробуйте ввести ещё раз.");
                    System.out.println("***");

            }
        }
    }

    public ArrayList<SubTask> showAllSubTasks(Integer enteredId) {
        Task taskInEditMode = TASK_STORAGE.get(enteredId);
        return taskInEditMode.getSUB_TASKS();
    }

    public String updateSubTaskStatus(Scanner scanner, int enteredId) {
        scanner.nextLine();
        System.out.println("Введите имя действия (subTask):");
        String enteredSubTaskName = scanner.nextLine();
        Task taskInEditMode = TASK_STORAGE.get(enteredId);
        return taskInEditMode.updateSubTaskStatus(enteredSubTaskName);
    }

    public String deleteOneTask(int enteredId) {
        Task taskInEditMode = TASK_STORAGE.get(enteredId);
        String deletedTaskName = taskInEditMode.getTaskName();
        TASK_STORAGE.remove(enteredId);
        return deletedTaskName;
    }

    public String clearAllTasks() {
        TASK_STORAGE.clear();
        return "Список всех задач (Task) пуст;";
    }

    public SubTask deleteOneSubTask(int enteredId, String subTaskName) {
        return TASK_STORAGE.get(enteredId).removeOneSubTask(subTaskName);
    }

    public boolean checkKey(Integer enteredId) {
        if (!TASK_STORAGE.containsKey(enteredId)) {
            System.out.println("Задача с ID: " + enteredId + " не найдена.");
            return false;
        }
        return true;
    }

    public void preGenerateTasks() {
        Task newTask1 = new Task("Иностранный язык", "Повысить уровень ин.яз.");
        newTask1.addNewSubTask("Речь", "Выполнить 1000 упражнений на произношение;");
        newTask1.addNewSubTask("Аудирование", "Выполнить 1000 упражнений на аудирование;");
        newTask1.addNewSubTask("Слова", "Пополнить словарный запас на 2000 новых слов;");
        newTask1.addNewSubTask("Чтение", "Прочитать 20 новых историй;");

        Task newTask2 = new Task("Иммунитет", "Повысить иммунитет");
        newTask2.addNewSubTask("Пробуждение", "Выпить стакан теплой воды;");
        newTask2.addNewSubTask("Разминка", "Ежедневно выполнять более 10 минут " +
                "разминки после пробуждения;");
        newTask2.addNewSubTask("Физическая культура", "Еженедельно выполнять 3 тренировки:" +
                "<силовая, кардио, растяжка> более 20 минут каждая;");
        newTask2.addNewSubTask("Прогулки", "Прогулки на свежем воздухе, " +
                "стараться проходить более 5000 шагов в день;");
        newTask2.addNewSubTask("Есть овощи", "Ежедневно съедать не менее 100 грамм " +
                "свежих овощей;");
        newTask2.addNewSubTask("Есть фрукты, ягоды", "Ежедневно съедать не менее 100 грамм " +
                "свежих фруктов или ягод;");

        Task newTask3 = new Task("Кругозор", "Расшить знания в разных областях");
        newTask3.addNewSubTask("Литература", "Читать более 5 книг в год;");
        newTask3.addNewSubTask("Искусство", "Посещать более 2 выставок в год;");
        newTask3.addNewSubTask("Музыка", "Посещать более 2 концертов в год");
        newTask3.addNewSubTask("Наука", "Читать более 5 научных статей в год");
        TASK_STORAGE.put(newTask1.getNUMBER_ID(), newTask1);
        TASK_STORAGE.put(newTask2.getNUMBER_ID(), newTask2);
        TASK_STORAGE.put(newTask3.getNUMBER_ID(), newTask3);
    }
}
