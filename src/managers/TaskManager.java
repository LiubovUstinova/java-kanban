package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utils.IdGenerator;
import utils.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final HashMap<Integer, Task> taskStorage;
    private final HashMap<Integer, Epic> epicStorage;
    private final HashMap<Integer, SubTask> subTaskStorage;

    public TaskManager() {
        taskStorage = new HashMap<>();
        epicStorage = new HashMap<>();
        subTaskStorage = new HashMap<>();
    }

    private Task saveNewTask(Task task) {
        Integer id = IdGenerator.generateTaskId();
        task.setId(id);
        task.setStatus(Status.NEW);
        taskStorage.put(id, task);
        return task;
    }

    private Epic saveNewEpic(Epic epic) {
        Integer id = IdGenerator.generateEpicId();
        epic.setId(id);
        epic.setStatus(Status.NEW);
        epicStorage.put(id, epic);
        return epic;
    }

    public SubTask saveNewSubTask(SubTask subTask, int epicId) {
        Integer id = IdGenerator.generateSubTaskId();
        subTask.setId(id);
        subTask.setEpicId(epicId);
        subTask.setStatus(Status.NEW);
        subTaskStorage.put(id, subTask);
        checkEpicStatus(epicId);
        return subTask;
    }

    public Task saveOrUpdateTask(Task task) {
        if (task.getId() == null) {
            return saveNewTask(task);
        }
        if (task.getStatus() == null) task.setStatus(Status.NEW);
        taskStorage.put(task.getId(), task);
        return task;
    }

    public Epic saveOrUpdateEpic(Epic epic) {
        if (epic.getId() == null) {
            return saveNewEpic(epic);
        }
        if (epic.getStatus() == null) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(epicStorage.get(epic.getId()).getStatus()); // user can't edit epic status
        }
        epicStorage.put(epic.getId(), epic);
        return epic;
    }

    public SubTask updateSubTask(SubTask subtask) {
        subTaskStorage.put(subtask.getId(), subtask);
        checkEpicStatus(subtask.getEpicId());
        return subtask;
    }

    public Task deleteTask(int id) {
        Task taskToDelete = taskStorage.get(id);
        taskStorage.remove(id);
        return taskToDelete;
    }

    public Epic deleteEpic(int id) {
        Epic epicToDelete = epicStorage.get(id);
        epicStorage.remove(id);
        for (int i = 0; i < subTaskStorage.size(); i++) {
            if (subTaskStorage.get(i + 1).getEpicId() == id) {
                subTaskStorage.remove(i);
            }
        }
        return epicToDelete;
    }

    public SubTask deleteSubTask(int id) {
        SubTask subTaskToDelete = subTaskStorage.get(id);
        subTaskStorage.remove(id);
        checkEpicStatus(id);
        return subTaskToDelete;
    }

    public void deleteAllTasks() {
        taskStorage.clear();
    }

    public void deleteAllEpics() {
        epicStorage.clear();
        subTaskStorage.clear();
    }

    public void deleteAllSubTasks() {
        subTaskStorage.clear();
        for (int i = 0; i < epicStorage.size(); i++) {
            epicStorage.get(i + 1).setStatus(Status.NEW);
        }
    }

    public Task getTask(int id) {
        return taskStorage.get(id);
    }

    public Epic getEpic(int id) {
        return epicStorage.get(id);
    }

    public SubTask getSubTask(int id) {
        return subTaskStorage.get(id);
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList= new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : taskStorage.entrySet()) {
            taskList.add(entry.getValue());
        }
        return taskList;
    }

    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> epicList= new ArrayList<>();
        for (Map.Entry<Integer, Epic> entry : epicStorage.entrySet()) {
            epicList.add(entry.getValue());
        }
        return epicList;
    }

    public ArrayList<SubTask> getAllSubTasks(int epicId) {
        ArrayList<SubTask> subTaskList= new ArrayList<>();
        for (int i = 0; i < subTaskStorage.size(); i++) {
            if (subTaskStorage.get(i + 1).getEpicId() == epicId) {
                subTaskList.add(subTaskStorage.get(i + 1));
            }
        }
        return subTaskList;
    }

    public Task setTaskStatus (int taskId, Status status) {
        Task taskInEditMode = taskStorage.get(taskId);
        taskInEditMode.setStatus(status);
        return taskInEditMode;
    }

    public SubTask setSubTaskStatus (int subTaskId, Status status) {
        SubTask subTaskInEditMode = subTaskStorage.get(subTaskId);
        subTaskInEditMode.setStatus(status);
        checkEpicStatus(subTaskInEditMode.getEpicId());
        return subTaskInEditMode;
    }



//    public boolean checkKey(Integer enteredId) {
//        if (!taskStorage.containsKey(enteredId)) {
//            System.out.println("Задача с ID: " + enteredId + " не найдена.");
//            return false;
//        }
//        return true;
//    }

    public void preGenerateTasks() {
        Epic newEpic1 = new Epic("Иностранный язык", "Повысить уровень ин.яз.");
        SubTask subTask1 = new SubTask("Речь", "Выполнить 1000 упражнений на произношение;");
        SubTask subTask2 = new SubTask("Аудирование", "Выполнить 1000 упражнений на аудирование;");
        SubTask subTask3 = new SubTask("Слова", "Пополнить словарный запас на 2000 новых слов;");
        SubTask subTask4 = new SubTask("Чтение", "Прочитать 20 новых историй;");
        saveOrUpdateEpic(newEpic1);
        saveNewSubTask(subTask1, newEpic1.getId());
        saveNewSubTask(subTask2, newEpic1.getId());
        saveNewSubTask(subTask3, newEpic1.getId());
        saveNewSubTask(subTask4, newEpic1.getId());

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
        saveOrUpdateEpic(newEpic2);
        saveNewSubTask(subTask5, newEpic2.getId());
        saveNewSubTask(subTask6, newEpic2.getId());
        saveNewSubTask(subTask7, newEpic2.getId());
        saveNewSubTask(subTask8, newEpic2.getId());
        saveNewSubTask(subTask9, newEpic2.getId());
        saveNewSubTask(subTask10, newEpic2.getId());

        Epic newEpic3 = new Epic("Кругозор", "Расшить знания в разных областях");
        SubTask subTask11 = new SubTask("Литература", "Читать более 5 книг в год;");
        SubTask subTask12 = new SubTask("Искусство", "Посещать более 2 выставок в год;");
        SubTask subTask13 = new SubTask("Музыка", "Посещать более 2 концертов в год");
        SubTask subTask14 = new SubTask("Наука", "Читать более 5 научных статей в год");
        saveOrUpdateEpic(newEpic3);
        saveNewSubTask(subTask11, newEpic3.getId());
        saveNewSubTask(subTask12, newEpic3.getId());
        saveNewSubTask(subTask13, newEpic3.getId());
        saveNewSubTask(subTask14, newEpic3.getId());

        Task newTask1 = new Task("Выучить песню", "На иностранном языке");
        Task newTask2 = new Task ("Погулять", "Будет хорошая погода");
        saveOrUpdateTask(newTask1);
        saveOrUpdateTask(newTask2);

    }

    private void checkEpicStatus(int epicId) {
        Epic checkedEpic = epicStorage.get(epicId);
        int statusNew = 0;
        int statusInProgress = 0;
        int statusDone = 0;
        if (subTaskStorage.isEmpty()) {
            checkedEpic.setStatus(Status.NEW);
        } else {
            for (int i = 0; i < subTaskStorage.size(); i++) {
                SubTask checkedSubTask = subTaskStorage.get(i + 1);
                if (checkedSubTask.getEpicId() == epicId) {
                    if (checkedSubTask.getStatus().equals(Status.NEW)) statusNew++;
                    if (checkedSubTask.getStatus().equals(Status.IN_PROGRESS)) statusInProgress++;
                    if (checkedSubTask.getStatus().equals(Status.DONE)) statusDone++;
                }
            }
            if ((statusInProgress + statusDone) == 0) checkedEpic.setStatus(Status.NEW);
            if ((statusNew + statusInProgress) == 0 && statusDone > 0) checkedEpic.setStatus(Status.DONE);
            if ((statusNew > 0 && statusDone > 0) || statusInProgress > 0) checkedEpic.setStatus(Status.IN_PROGRESS);
        }
    }
}
