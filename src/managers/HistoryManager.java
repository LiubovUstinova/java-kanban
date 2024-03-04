package managers;

import tasks.Task;

import java.util.ArrayList;

public interface HistoryManager {

    void updateHistory(Task task);

    ArrayList<Task> getHistory();
}
