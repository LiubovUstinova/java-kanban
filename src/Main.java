import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Task> taskStorage = new HashMap<>();
        preGenerateTasks(taskStorage);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Поехали!");
        while (true) {
            printMenu();
            int actionNumber = scanner.nextInt();
            scanner.nextLine();

            switch (actionNumber) {
                case 1:
                    showAllTasks(taskStorage);
                    System.out.println("*****");
                    break;
                case 2:
                    createANewTask(scanner, taskStorage);
                    System.out.println("*****");
                    break;
                case 3:
                    if (!taskStorage.isEmpty()) recordTaskActions(scanner, taskStorage);
                    System.out.println("*****");
                    break;
                case 4:
                    if (!taskStorage.isEmpty()) showAllSubTasks(scanner, taskStorage);
                    System.out.println("*****");
                    break;
                case 5:
                    if (!taskStorage.isEmpty()) updateSubTaskStatus(scanner, taskStorage);
                    System.out.println("*****");
                    break;
                case 6:
                    if (!taskStorage.isEmpty()) deleteOneTask(scanner, taskStorage);
                    System.out.println("*****");
                    break;
                case 7:
                    taskStorage.clear();
                    System.out.println("Список всех задач (Task) пуст;");
                    System.out.println("*****");
                    break;
                case 8:
                    System.out.println("Вы вышли из программы Организатор задач.");
                    System.out.println("Желаем продуктивного дня,");
                    System.out.println("И ждём Вас снова!");
                    System.out.println("*****");
                    return;
                default:
                    System.out.println("Такого пункта меню нет, попробуйте ввести ещё раз.");
                    System.out.println("*****");
            }
        }


    }

    private static void printMenu() {
        System.out.println("Вас приветствует программа Организатор задач!");
        System.out.println("Выберете и введите номер пункта меню:");
        System.out.println("1 - получить список всех задач;");
        System.out.println("2 - создать новую задачу;");
        System.out.println("3 - записать действия для выполнения задачи;");
        System.out.println("4 - получить список действий одной задачи;");
        System.out.println("5 - подтвердить выполнения действия в задаче;");
        System.out.println("6 - удалить одну задачу;");
        System.out.println("7 - удалить список всех задач;");
        System.out.println("8 - Выйти из программы;");
    }

    private static void showAllTasks(HashMap<Integer, Task> taskStorage) {
        for (Map.Entry<Integer, Task> entry : taskStorage.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
//                    for (Integer key : taskStorage.keySet()) {
//                        System.out.println(key + ": " + taskStorage.get(key));
//    }
    }

    private static void createANewTask(Scanner scanner, HashMap<Integer, Task> taskStorage) {
        System.out.println("Введите имя задачи (Task):");
        String enteredName = scanner.nextLine();
        System.out.println("Введите описание задачи (Task):");
        String enteredDescription = scanner.nextLine();
        Task createdTask = new Task(enteredName, enteredDescription);
        taskStorage.put(createdTask.getNUMBER_ID(), createdTask);
        System.out.println("Задачи присвоен NUMBER_ID: " + createdTask.getNUMBER_ID());
    }

    private static void recordTaskActions(Scanner scanner, HashMap<Integer, Task> taskStorage) {
        System.out.println("Введите NUMBER_ID");
        Integer enteredId = scanner.nextInt();
        if (!checkKey(enteredId, taskStorage)) return;
        Task taskInEditMode = taskStorage.get(enteredId);
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

    private static void showAllSubTasks(Scanner scanner, HashMap<Integer, Task> taskStorage) {
        System.out.println("Введите NUMBER_ID");
        Integer enteredId = scanner.nextInt();
        if (!checkKey(enteredId, taskStorage)) return;
        Task taskInEditMode = taskStorage.get(enteredId);
        taskInEditMode.showSubTasks();
    }

    private static void updateSubTaskStatus(Scanner scanner, HashMap<Integer, Task> taskStorage) {
        System.out.println("Введите NUMBER_ID");
        int enteredId = scanner.nextInt();
        if (!checkKey(enteredId, taskStorage)) return;
        scanner.nextLine();
        System.out.println("Введите имя действия (subTask):");
        String enteredSubTaskName = scanner.nextLine();
        Task taskInEditMode = taskStorage.get(enteredId);
        System.out.println(taskInEditMode.updateSubTaskStatus(enteredSubTaskName));
    }

    private static void deleteOneTask(Scanner scanner, HashMap<Integer, Task> taskStorage) {
        System.out.println("Введите NUMBER_ID");
        Integer enteredId = scanner.nextInt();
        if (!checkKey(enteredId, taskStorage)) return;
        Task taskInEditMode = taskStorage.get(enteredId);
        String deletedTaskName = taskInEditMode.getTaskName();
        taskStorage.remove(enteredId);
        System.out.println("Удалена одна задача: " + deletedTaskName);
    }

    private static boolean checkKey(Integer enteredId, HashMap<Integer, Task> taskStorage) {
        if (!taskStorage.containsKey(enteredId)) {
            System.out.println("Задача с ID: " + enteredId + " не найдена.");
            return false;
        }
        return true;
    }

    private static void preGenerateTasks(HashMap<Integer, Task> taskStorage) {
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
        taskStorage.put(newTask1.getNUMBER_ID(), newTask1);
        taskStorage.put(newTask2.getNUMBER_ID(), newTask2);
        taskStorage.put(newTask3.getNUMBER_ID(), newTask3);
    }
}
