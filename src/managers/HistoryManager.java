package managers;

import tasks.Task;

import java.util.List;

public interface HistoryManager {

    void updateHistory(Task task);

    List<Task> getHistory();
}
