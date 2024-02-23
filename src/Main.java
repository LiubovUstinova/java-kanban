import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.preGenerateTasks();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Поехали!");
        while (true) {
            printMenu();
            int actionNumber = scanner.nextInt();
            scanner.nextLine();

            switch (actionNumber) {
                case 1:
                    for (Map.Entry<Integer, Task> entry : taskManager.getTASK_STORAGE().entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
//                    for (Integer key : taskManager.getTaskStorage().keySet()) {
//                        System.out.println(key + ": " + taskManager.getTaskStorage().get(key));
//    }
                    System.out.println("*****");
                    break;
                case 2:
                    System.out.println("Введите имя задачи (Task):");
                    String enteredName = scanner.nextLine();
                    System.out.println("Введите описание задачи (Task):");
                    String enteredDescription = scanner.nextLine();
                    int newTaskId = taskManager.createANewTask(enteredName, enteredDescription);
                    System.out.println("Задачи присвоен NUMBER_ID: " + newTaskId);
                    System.out.println("*****");
                    break;
                case 3:
                    if (!taskManager.getTASK_STORAGE().isEmpty()) {
                        System.out.println("Введите NUMBER_ID");
                        int enteredId = scanner.nextInt();
                        if (taskManager.checkKey(enteredId)) {
                            scanner.nextLine();
                            System.out.println("Введите новое название задачи или нажмите Enter," +
                                    " чтобы оставить прежнее название:");
                            String taskNewName = scanner.nextLine();
                            System.out.println("Введите новое описание задачи или нажмите Enter," +
                                    " чтобы оставить прежнее описание:");
                            String taskNewDescription = scanner.nextLine();
                            taskManager.changeTask(enteredId, taskNewName, taskNewDescription);
                        }
                    }
                    System.out.println("*****");
                    break;
                case 4:
                    if (!taskManager.getTASK_STORAGE().isEmpty()) {
                        System.out.println("Введите NUMBER_ID");
                        int enteredId = scanner.nextInt();
                        if (taskManager.checkKey(enteredId)) {
                            taskManager.recordTaskActions(scanner, enteredId);
                        }
                    }
                    System.out.println("*****");
                    break;
                case 5:
                    if (!taskManager.getTASK_STORAGE().isEmpty()) {
                        System.out.println("Введите NUMBER_ID");
                        Integer enteredId = scanner.nextInt();
                        if (taskManager.checkKey(enteredId)) {
                            ArrayList<SubTask> subTaskList = taskManager.showAllSubTasks(enteredId);
                            if (!subTaskList.isEmpty()) {
                                for (SubTask subTask : subTaskList) {
                                    System.out.println(subTask);
                                }
                            }
                        }
                    }
                    System.out.println("*****");
                    break;
                case 6:
                    if (!taskManager.getTASK_STORAGE().isEmpty()) {
                        System.out.println("Введите NUMBER_ID");
                        int enteredId = scanner.nextInt();
                        if (taskManager.checkKey(enteredId)) {
                            System.out.println(taskManager.updateSubTaskStatus(scanner, enteredId));
                        }
                    }
                    System.out.println("*****");
                    break;
                case 7:
                    if (!taskManager.getTASK_STORAGE().isEmpty()) {
                        System.out.println("Введите NUMBER_ID");
                        int enteredId = scanner.nextInt();
                        if (taskManager.checkKey(enteredId)) {
                            System.out.println("Удалена одна задача: " + taskManager.deleteOneTask(enteredId));
                        }
                    }
                    System.out.println("*****");
                    break;
                case 8:
                    System.out.println(taskManager.clearAllTasks());
                    System.out.println("*****");
                    break;
                case 9:
                    if (!taskManager.getTASK_STORAGE().isEmpty()) {
                        System.out.println("Введите NUMBER_ID");
                        int enteredId = scanner.nextInt();
                        if (taskManager.checkKey(enteredId)) {
                            scanner.nextLine();
                            System.out.println("Введите имя действия:");
                            String enteredSubTaskName = scanner.nextLine();
                            SubTask removeSubTask = taskManager.deleteOneSubTask(enteredId, enteredSubTaskName);
                            if (removeSubTask == null) {
                                System.out.println("Действие " + enteredSubTaskName + " не найдено;");
                            } else {
                                System.out.println("Удалено действие с именем " + removeSubTask.getSubTaskName() +
                                        " и описанием " + removeSubTask.getSubTaskDescription());
                            }
                        }
                    }
                    System.out.println("*****");
                    break;
                case 0:
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
        System.out.println("3 - перезаписать имя и описание задачи;");
        System.out.println("4 - записать действия для выполнения задачи;");
        System.out.println("5 - получить список действий одной задачи;");
        System.out.println("6 - подтвердить выполнения действия в задаче;");
        System.out.println("7 - удалить одну задачу;");
        System.out.println("8 - удалить список всех задач;");
        System.out.println("9 - удалить одно действие;");
        System.out.println("0 - Выйти из программы;");
    }

}
