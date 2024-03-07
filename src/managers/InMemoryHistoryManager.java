package managers;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> historyList;
    private final int historySize = 10;

    public InMemoryHistoryManager() {
        this.historyList = new ArrayList<>(historySize);
    }

    @Override
    public void updateHistory(Task task) {
        if (historyList.size() == historySize) {
            historyList.removeLast();
        }
        historyList.addFirst(task);
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(historyList);
        // Новый лист, чтобы оригинальный нельзя было изменить - так было сказано на вебинаре
    }
}
