package managers;

import org.junit.jupiter.api.Test;
import tasks.Task;
import utils.Managers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    // Судя по всему, JUnit инициализирует поля при каждом запуске теста. Это было также замечено на вебинаре.
    // Таким образом, создание конструктора с @BeforeEach кажется излишним.
    HistoryManager historyManager = Managers.getDefaultHistoryManager();

    @Test
    void checkIfUpdateHistoryOk() {
        Task task = new Task("X", "Y");
        historyManager.updateHistory(task);

        final List<Task> history = historyManager.getHistory();

        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }
}