import managers.TaskManager;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utils.Status;
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
                    System.out.println(taskManager.getAllTasks());
                    System.out.println("*****");
                    break;
                case 2:
                    System.out.println(taskManager.getAllEpics());
                    System.out.println("*****");
                    break;
                case 3:
                    System.out.println("Введите номер глобальной задачи:");
                    int epicId = scanner.nextInt();
                    System.out.println(taskManager.getAllSubTasks(epicId));
                    System.out.println("*****");
                    break;
                case 4:
                    System.out.println("Введите имя новой задачи:");
                    String newTaskName = scanner.nextLine();
                    System.out.println("Введите описание новой задачи:");
                    String newTaskDescription = scanner.nextLine();
                    Task newTask = new Task(newTaskName, newTaskDescription);

                    System.out.println("Создана новая задача: " + taskManager.saveOrUpdateTask(newTask));
                    System.out.println("*****");
                    break;
                case 5:
                    System.out.println("Введите имя новой глобальной задачи:");
                    String newEpicName = scanner.nextLine();
                    System.out.println("Введите описание новой глобальной задачи:");
                    String newEpicDescription = scanner.nextLine();
                    Epic newEpic = new Epic(newEpicName, newEpicDescription);

                    System.out.println("Создана новая глобальная задача: " + taskManager.saveOrUpdateEpic(newEpic));
                    System.out.println("*****");
                    break;
                case 6:
                    System.out.println("Введите номер глобальной задачи, для которой требуется добавить действие:");
                    int epicIdToAddSubTask = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите имя нового действия:");
                    String newSubTaskName = scanner.nextLine();
                    System.out.println("Введите описание нового действия:");
                    String newSubTaskDescription = scanner.nextLine();
                    SubTask newSubTask = new SubTask(newSubTaskName, newSubTaskDescription);

                    System.out.println("Создано новое действие: " +
                            taskManager.saveNewSubTask(newSubTask, epicIdToAddSubTask));
                    System.out.println("*****");
                    break;
                case 7:
                    System.out.println("Введите номер задачи для отображения:");
                    int taskToShow = scanner.nextInt();
                    System.out.println(taskManager.getTask(taskToShow));
                    System.out.println("*****");
                    break;
                case 8:
                    System.out.println("Введите номер глобальной задачи для отображения:");
                    int epicToShow = scanner.nextInt();
                    System.out.println(taskManager.getEpic(epicToShow));
                    System.out.println("*****");
                    break;
                case 9:
                    System.out.println("Введите номер действия для отображения:");
                    int subTaskToShow = scanner.nextInt();
                    System.out.println(taskManager.getTask(subTaskToShow));
                    System.out.println("*****");
                    break;
                case 10:
                    System.out.println("Введите номер задачи для редактирования:");
                    int taskIdToEdit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите новое название задачи:");
                    String taskNewName = scanner.nextLine();
                    System.out.println("Введите новое описание задачи:");
                    String taskNewDescription = scanner.nextLine();
                    Status taskToEditStatus = taskManager.getTask(taskIdToEdit).getStatus();
                    Task taskToEdit = new Task(taskNewName, taskNewDescription);
                    taskToEdit.setId(taskIdToEdit);
                    taskToEdit.setStatus(taskToEditStatus);

                    System.out.println("Задача обновлена:" + taskManager.saveOrUpdateTask(taskToEdit));
                    System.out.println("*****");
                    break;
                case 11:
                    System.out.println("Введите номер глобальной задачи для редактирования:");
                    int epicIdToEdit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите новое глобальной название задачи:");
                    String epicNewName = scanner.nextLine();
                    System.out.println("Введите новое глобальной описание задачи:");
                    String epicNewDescription = scanner.nextLine();
                    Epic epicToEdit = new Epic(epicNewName, epicNewDescription);
                    epicToEdit.setId(epicIdToEdit);

                    System.out.println("Глобальная задача обновлена:" + taskManager.saveOrUpdateEpic(epicToEdit));
                    System.out.println("*****");
                    break;
                case 12:
                    System.out.println("Введите номер действия для редактирования:");
                    int subTaskIdToEdit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите новое название действия:");
                    String subTaskNewName = scanner.nextLine();
                    System.out.println("Введите новое описание действия:");
                    String subTaskNewDescription = scanner.nextLine();
                    Status subTaskToEditStatus = taskManager.getSubTask(subTaskIdToEdit).getStatus();
                    Integer subTaskToEditEpicId = taskManager.getSubTask(subTaskIdToEdit).getEpicId();
                    SubTask subTaskToEdit = new SubTask(subTaskNewName, subTaskNewDescription);
                    subTaskToEdit.setId(subTaskIdToEdit);
                    subTaskToEdit.setStatus(subTaskToEditStatus);
                    subTaskToEdit.setEpicId(subTaskToEditEpicId);

                    System.out.println("Действие обновлено:" + taskManager.updateSubTask(subTaskToEdit));
                    System.out.println("*****");
                    break;
                case 13:
                    System.out.println("Введите номер задачи для смены статуса:");
                    int taskIdToChangeStatus = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите статус: ");
                    System.out.println("1 для отметки, что задача выполняется;");
                    System.out.println("2 для отметки, что задача выполнена.");
                    int statusTaskCode = scanner.nextInt();
                    switch (statusTaskCode) {
                        case 1:
                            System.out.println("Статус обновлён:");
                            System.out.println(taskManager.
                                    setTaskStatus(taskIdToChangeStatus, Status.IN_PROGRESS));
                            break;
                        case 2:
                            System.out.println("Статус обновлён:");
                            System.out.println(taskManager.
                                    setTaskStatus(taskIdToChangeStatus, Status.DONE));
                            break;
                        default:
                            System.out.println("Неверный код статуса.");
                    }
                    System.out.println("*****");
                    break;
                case 14:
                    System.out.println("Введите номер действия для смены статуса:");
                    int subTaskIdToChangeStatus = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите статус: ");
                    System.out.println("1 для отметки, что действие выполняется;");
                    System.out.println("2 для отметки, что действие выполнено.");
                    int statusSubTaskCode = scanner.nextInt();
                    switch (statusSubTaskCode) {
                        case 1:
                            System.out.println("Статус обновлён:");
                            System.out.println(taskManager.
                                    setSubTaskStatus(subTaskIdToChangeStatus, Status.IN_PROGRESS));
                            break;
                        case 2:
                            System.out.println("Статус обновлён:");
                            System.out.println(taskManager.
                                    setSubTaskStatus(subTaskIdToChangeStatus, Status.DONE));
                            break;
                        default:
                            System.out.println("Неверный код статуса.");
                    }
                    System.out.println("*****");
                    break;
                case 15:
                    System.out.println("Введите номер задачи для удаления:");
                    int taskToDeleteId = scanner.nextInt();

                    System.out.println("Удалена задача:" + taskManager.deleteTask(taskToDeleteId));
                    System.out.println("*****");
                    break;
                case 16:
                    System.out.println("Введите номер глобальной задачи для удаления " +
                            "(это удалит все связанные с ней действия):");
                    int epicToDeleteId = scanner.nextInt();

                    System.out.println("Удалена глобальная задача:" + taskManager.deleteEpic(epicToDeleteId));
                    System.out.println("*****");
                    break;
                case 17:
                    System.out.println("Введите номер действия для удаления:");
                    int subTaskToDeleteId = scanner.nextInt();

                    System.out.println("Удалено действие:" + taskManager.deleteSubTask(subTaskToDeleteId));
                    System.out.println("*****");
                    break;
                case 18:
                    taskManager.deleteAllTasks();
                    System.out.println("Все задачи удалены.");
                    System.out.println("*****");
                    break;
                case 19:
                    taskManager.deleteAllEpics();
                    System.out.println("Все задачи (и действия) удалены.");
                    System.out.println("*****");
                    break;
                case 20:
                    taskManager.deleteAllSubTasks();
                    System.out.println("Все действия для глобальных задач удалены.");
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
        System.out.println("2 - получить список всех глобальных задач;");
        System.out.println("3 - получить список всех действий определённой глобальный задачи;");
        System.out.println("4 - создать новую задачу;");
        System.out.println("5 - создать новую глобальную задачу;");
        System.out.println("6 - создать новое действие определённой глобальной задачи;");
        System.out.println("7 - вывести информацию об одной задаче;");
        System.out.println("8 - вывести информацию об одной глобальной задаче;");
        System.out.println("9 - вывести информацию об одном действии;");
        System.out.println("10 - обновить задачу;");
        System.out.println("11 - обновить глобальную задачу;");
        System.out.println("12 - обновить действие;");
        System.out.println("13 - поменять статус задачи;");
        System.out.println("14 - поменять статус действия;");
        System.out.println("15 - удалить одну задачу;");
        System.out.println("16 - удалить одну глобальную задачу (и все связанные с ней действия);");
        System.out.println("17 - удалить одно действие;");
        System.out.println("18 - очистить список задач;");
        System.out.println("19 - очистить список глобальных задач (и действий);");
        System.out.println("20 - очистить список всех действий;");
        System.out.println("0 - Выйти из программы;");
    }

}
