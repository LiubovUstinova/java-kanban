import managers.TaskManager;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utils.Status;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        preGenerateTasks(taskManager);
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

                    System.out.println("Создана новая задача: " + taskManager.saveNewTask(newTask));
                    System.out.println("*****");
                    break;
                case 5:
                    System.out.println("Введите имя новой глобальной задачи:");
                    String newEpicName = scanner.nextLine();
                    System.out.println("Введите описание новой глобальной задачи:");
                    String newEpicDescription = scanner.nextLine();
                    Epic newEpic = new Epic(newEpicName, newEpicDescription);

                    System.out.println("Создана новая глобальная задача: " + taskManager.saveNewEpic(newEpic));
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
                    newSubTask.setEpicId(epicIdToAddSubTask);
                    newSubTask.setStatus(Status.NEW);

                    System.out.println("Создано новое действие: " +
                            taskManager.saveNewSubTask(newSubTask));
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

                    System.out.println("Задача обновлена:" + taskManager.updateTask(taskToEdit));
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

                    System.out.println("Глобальная задача обновлена:" + taskManager.updateEpic(epicToEdit));
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

    private static void preGenerateTasks(TaskManager taskManager) {
        Epic newEpic1 = new Epic("Иностранный язык", "Повысить уровень ин.яз.");
        SubTask subTask1 = new SubTask("Речь", "Выполнить 1000 упражнений на произношение;");
        SubTask subTask2 = new SubTask("Аудирование", "Выполнить 1000 упражнений на аудирование;");
        SubTask subTask3 = new SubTask("Слова", "Пополнить словарный запас на 2000 новых слов;");
        SubTask subTask4 = new SubTask("Чтение", "Прочитать 20 новых историй;");
        taskManager.saveNewEpic(newEpic1);
        subTask1.setEpicId(newEpic1.getId());
        subTask1.setStatus(Status.NEW);
        subTask2.setEpicId(newEpic1.getId());
        subTask2.setStatus(Status.IN_PROGRESS);
        subTask3.setEpicId(newEpic1.getId());
        subTask3.setStatus(Status.NEW);
        subTask4.setEpicId(newEpic1.getId());
        subTask4.setStatus(Status.DONE);
        taskManager.saveNewSubTask(subTask1);
        taskManager.saveNewSubTask(subTask2);
        taskManager.saveNewSubTask(subTask3);
        taskManager.saveNewSubTask(subTask4);

        Epic newEpic2 = new Epic("Иммунитет", "Повысить иммунитет");
        SubTask subTask5 = new SubTask("Пробуждение", "Выпить стакан теплой воды;");
        SubTask subTask6 = new SubTask("Разминка", "Ежедневно выполнять более 10 минут " +
                "разминки после пробуждения;");
        SubTask subTask7 = new SubTask("Физическая культура", "Еженедельно выполнять 3 тренировки:" +
                "<силовая, кардио, растяжка> более 20 минут каждая;");
        SubTask subTask8 = new SubTask("Прогулки", "Прогулки на свежем воздухе, " +
                "стараться проходить более 5000 шагов в день;");
        SubTask subTask9 = new SubTask("Есть овощи", "Ежедневно съедать не менее 100 грамм " +
                "свежих овощей;");
        SubTask subTask10 = new SubTask("Есть фрукты, ягоды", "Ежедневно съедать не менее 100 грамм " +
                "свежих фруктов или ягод;");
        taskManager.saveNewEpic(newEpic2);
        subTask5.setEpicId(newEpic2.getId());
        subTask5.setStatus(Status.NEW);
        subTask6.setEpicId(newEpic2.getId());
        subTask6.setStatus(Status.IN_PROGRESS);
        subTask7.setEpicId(newEpic2.getId());
        subTask7.setStatus(Status.NEW);
        subTask8.setEpicId(newEpic2.getId());
        subTask8.setStatus(Status.DONE);
        subTask9.setEpicId(newEpic2.getId());
        subTask9.setStatus(Status.DONE);
        subTask10.setEpicId(newEpic2.getId());
        subTask10.setStatus(Status.DONE);
        taskManager.saveNewSubTask(subTask5);
        taskManager.saveNewSubTask(subTask6);
        taskManager.saveNewSubTask(subTask7);
        taskManager.saveNewSubTask(subTask8);
        taskManager.saveNewSubTask(subTask9);
        taskManager.saveNewSubTask(subTask10);

        Epic newEpic3 = new Epic("Кругозор", "Расшить знания в разных областях");
        SubTask subTask11 = new SubTask("Литература", "Читать более 5 книг в год;");
        SubTask subTask12 = new SubTask("Искусство", "Посещать более 2 выставок в год;");
        SubTask subTask13 = new SubTask("Музыка", "Посещать более 2 концертов в год");
        SubTask subTask14 = new SubTask("Наука", "Читать более 5 научных статей в год");
        taskManager.saveNewEpic(newEpic3);
        subTask11.setEpicId(newEpic3.getId());
        subTask11.setStatus(Status.IN_PROGRESS);
        subTask12.setEpicId(newEpic3.getId());
        subTask12.setStatus(Status.IN_PROGRESS);
        subTask13.setEpicId(newEpic3.getId());
        subTask13.setStatus(Status.NEW);
        subTask14.setEpicId(newEpic3.getId());
        subTask14.setStatus(Status.NEW);
        taskManager.saveNewSubTask(subTask11);
        taskManager.saveNewSubTask(subTask12);
        taskManager.saveNewSubTask(subTask13);
        taskManager.saveNewSubTask(subTask14);

        Task newTask1 = new Task("Выучить песню", "На иностранном языке");
        Task newTask2 = new Task ("Погулять", "Будет хорошая погода");
        taskManager.saveNewTask(newTask1);
        taskManager.saveNewTask(newTask2);

    }

}
