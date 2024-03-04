package managers;

import tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Task> historyList;
    private final int HISTORY_SIZE = 10;

    public InMemoryHistoryManager() {
        this.historyList = new ArrayList<>(HISTORY_SIZE);
    }

    @Override
    public void updateHistory(Task task) {
        if (historyList.size() == HISTORY_SIZE) {
            historyList.removeLast();
        }
        historyList.addFirst(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return new ArrayList<>(historyList);
    }
}
