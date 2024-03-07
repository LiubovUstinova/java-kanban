package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utils.Status;

import java.util.List;

public interface TaskManager {
    Task saveNewTask(Task task);

    Epic saveNewEpic(Epic epic);

    SubTask saveNewSubTask(SubTask subTask);

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    SubTask updateSubTask(SubTask subtask);

    Task deleteTask(int id);

    Epic deleteEpic(int id);

    SubTask deleteSubTask(int id);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();

    Task getTask(int id);

    Epic getEpic(int id);

    SubTask getSubTask(int id);

    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<SubTask> getAllSubTasks(int epicId);

    Task setTaskStatus(int taskId, Status status);

    SubTask setSubTaskStatus(int subTaskId, Status status);

    List<Task> getHistory();
}
