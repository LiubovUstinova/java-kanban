package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utils.IdGenerator;
import utils.Status;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> taskStorage;
    private final HashMap<Integer, Epic> epicStorage;
    private final HashMap<Integer, SubTask> subTaskStorage;

    public TaskManager() {
        taskStorage = new HashMap<>();
        epicStorage = new HashMap<>();
        subTaskStorage = new HashMap<>();
    }

    public Task saveNewTask(Task task) {
        Integer id = IdGenerator.generateTaskId();
        task.setId(id);
        return taskStorage.put(id, task);
    }

    public Epic saveNewEpic(Epic epic) {
        Integer id = IdGenerator.generateTaskId();
        epic.setId(id);
        return epicStorage.put(id, epic);
    }

    public SubTask saveNewSubTask(SubTask subTask) {
        Integer id = IdGenerator.generateTaskId();
        subTask.setId(id);
        int epicId = subTask.getEpicId();
        subTaskStorage.put(id, subTask);
        epicStorage.get(epicId).addNewSubTaskId(id);
        checkEpicStatus(epicId);
        return subTask;
    }

    public Task updateTask(Task task) {
        return taskStorage.put(task.getId(), task);
    }

    public Epic updateEpic(Epic epic) {
        epic.setStatus(epicStorage.get(epic.getId()).getStatus()); // user can't edit epic status
        return epicStorage.put(epic.getId(), epic);
    }

    public SubTask updateSubTask(SubTask subtask) {
        subTaskStorage.put(subtask.getId(), subtask);
        checkEpicStatus(subtask.getEpicId());
        return subtask;
    }

    public Task deleteTask(int id) {
        return taskStorage.remove(id);
    }

    public Epic deleteEpic(int id) {
        ArrayList<Integer> subTasksToRemoveList = epicStorage.get(id).getSubTasksId();
        for (Integer integer : subTasksToRemoveList) {
            subTaskStorage.remove(integer);
        }
        return epicStorage.remove(id);
    }

    public SubTask deleteSubTask(int id) {
        SubTask subTaskToDelete = subTaskStorage.get(id);
        int epicId = subTaskToDelete.getEpicId();
        epicStorage.get(epicId).getSubTasksId().remove(id);
        checkEpicStatus(epicId);
        return subTaskStorage.remove(id);
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
        for (Epic epic : epicStorage.values()) {
            epic.setStatus(Status.NEW);
            epic.getSubTasksId().clear();
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
        return new ArrayList<>(taskStorage.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epicStorage.values());
    }

    public ArrayList<SubTask> getAllSubTasks(int epicId) {
        ArrayList<SubTask> subTaskList = new ArrayList<>();
        ArrayList<Integer> subTasksId = epicStorage.get(epicId).getSubTasksId();
        for (Integer integer : subTasksId) {
            subTaskList.add(subTaskStorage.get(integer));
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

    private void checkEpicStatus(int epicId) {
        Epic checkedEpic = epicStorage.get(epicId);
        ArrayList<Integer> subTasksId = checkedEpic.getSubTasksId();
        int statusNew = 0;
        int statusInProgress = 0;
        int statusDone = 0;
        if (subTasksId.isEmpty()) {
            checkedEpic.setStatus(Status.NEW);
        } else {
            for (Integer integer : subTasksId) {
                if (subTaskStorage.get(integer).getStatus().equals(Status.NEW)) statusNew++;
                if (subTaskStorage.get(integer).getStatus().equals(Status.IN_PROGRESS)) statusInProgress++;
                if (subTaskStorage.get(integer).getStatus().equals(Status.DONE)) statusDone++;
            }
            if ((statusInProgress + statusDone) == 0) checkedEpic.setStatus(Status.NEW);
            if ((statusNew + statusInProgress) == 0 && statusDone > 0) checkedEpic.setStatus(Status.DONE);
            if ((statusNew > 0 && statusDone > 0) || statusInProgress > 0) checkedEpic.setStatus(Status.IN_PROGRESS);
        }
    }
}
