package managers;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utils.IdGenerator;
import utils.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> taskStorage;
    private final Map<Integer, Epic> epicStorage;
    private final Map<Integer, SubTask> subTaskStorage;

    private final HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        taskStorage = new HashMap<>();
        epicStorage = new HashMap<>();
        subTaskStorage = new HashMap<>();
        this.historyManager = historyManager;
    }

    @Override
    public Task saveNewTask(Task task) {
        Integer id = IdGenerator.generateTaskId();
        task.setId(id);
        return taskStorage.put(id, task);
    }

    @Override
    public Epic saveNewEpic(Epic epic) {
        Integer id = IdGenerator.generateTaskId();
        epic.setId(id);
        return epicStorage.put(id, epic);
    }

    @Override
    public SubTask saveNewSubTask(SubTask subTask) {
        Integer id = IdGenerator.generateTaskId();
        subTask.setId(id);
        int epicId = subTask.getEpicId();
        subTaskStorage.put(id, subTask);
        epicStorage.get(epicId).addNewSubTaskId(id);
        checkEpicStatus(epicId);
        return subTask;
    }

    @Override
    public Task updateTask(Task task) {
        return taskStorage.put(task.getId(), task);
    }

    @Override
    public Epic updateEpic(Epic epic) {
        epic.setStatus(epicStorage.get(epic.getId()).getStatus()); // user can't edit epic status
        return epicStorage.put(epic.getId(), epic);
    }

    @Override
    public SubTask updateSubTask(SubTask subtask) {
        subTaskStorage.put(subtask.getId(), subtask);
        checkEpicStatus(subtask.getEpicId());
        return subtask;
    }

    @Override
    public Task deleteTask(int id) {
        return taskStorage.remove(id);
    }

    @Override
    public Epic deleteEpic(int id) {
        List<Integer> subTasksToRemoveList = epicStorage.get(id).getSubTasksId();
        for (Integer integer : subTasksToRemoveList) {
            subTaskStorage.remove(integer);
        }
        return epicStorage.remove(id);
    }

    @Override
    public SubTask deleteSubTask(int id) {
        SubTask subTaskToDelete = subTaskStorage.get(id);
        int epicId = subTaskToDelete.getEpicId();
        epicStorage.get(epicId).getSubTasksId().remove(id);
        checkEpicStatus(epicId);
        return subTaskStorage.remove(id);
    }

    @Override
    public void deleteAllTasks() {
        taskStorage.clear();
    }

    @Override
    public void deleteAllEpics() {
        epicStorage.clear();
        subTaskStorage.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        subTaskStorage.clear();
        for (Epic epic : epicStorage.values()) {
            epic.setStatus(Status.NEW);
            epic.getSubTasksId().clear();
        }
    }

    @Override
    public Task getTask(int id) {
        Task task = taskStorage.get(id);
        updateHistory(task);
        return task;
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = epicStorage.get(id);
        updateHistory(epic);
        return epic;
    }

    @Override
    public SubTask getSubTask(int id) {
        SubTask subTask = subTaskStorage.get(id);
        updateHistory(subTask);
        return subTask;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskStorage.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epicStorage.values());
    }

    @Override
    public List<SubTask> getAllSubTasks(int epicId) {
        List<SubTask> subTaskList = new ArrayList<>();
        List<Integer> subTasksId = epicStorage.get(epicId).getSubTasksId();
        for (Integer integer : subTasksId) {
            subTaskList.add(subTaskStorage.get(integer));
        }
        return subTaskList;
    }

    @Override
    public Task setTaskStatus(int taskId, Status status) {
        Task taskInEditMode = taskStorage.get(taskId);
        taskInEditMode.setStatus(status);
        return taskInEditMode;
    }

    @Override
    public SubTask setSubTaskStatus(int subTaskId, Status status) {
        SubTask subTaskInEditMode = subTaskStorage.get(subTaskId);
        subTaskInEditMode.setStatus(status);
        checkEpicStatus(subTaskInEditMode.getEpicId());
        return subTaskInEditMode;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    private void updateHistory(Task task) {
        historyManager.updateHistory(task);
    }

    private void checkEpicStatus(int epicId) {
        Epic checkedEpic = epicStorage.get(epicId);
        List<Integer> subTasksId = checkedEpic.getSubTasksId();
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
