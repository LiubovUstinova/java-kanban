package managers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.SubTask;
import tasks.Task;
import utils.Managers;
import utils.Status;
import java.util.List;

class InMemoryTaskManagerTest {

    // Судя по всему, JUnit инициализирует поля при каждом запуске теста. Это было также замечено на вебинаре.
    // Таким образом, создание конструктора с @BeforeEach кажется излишним.
    TaskManager taskManager = Managers.getDefaultTaskManager();

    @Test
    void checkIfIdIsOverwrittenWhenSavingNewTask() {
        Task taskNotNew = new Task("OldTaskName", "OldTaskDesc");
        int oldTaskId = 99999;
        taskNotNew.setId(oldTaskId);
        taskManager.saveNewTask(taskNotNew);
        Assertions.assertNotEquals(oldTaskId, taskNotNew.getId());
    }

    @Test
    void checkIfSavesTasks() {
        Task task1 = new Task("2", "B");
        taskManager.saveNewTask(task1);

        Task exctractedTask = taskManager.getTask(task1.getId());

        Assertions.assertEquals(task1, exctractedTask);
    }

    @Test
    void checkIfSavesEpics() {
        Epic epic1 = new Epic("3", "C");
        taskManager.saveNewEpic(epic1);

        Task extractedEpic = taskManager.getEpic(epic1.getId());

        Assertions.assertEquals(epic1, extractedEpic);
    }

    @Test
    void checkIfSavesSubTasks() {
        SubTask subTask1 = new SubTask("4", "D");
        Epic epic1 = new Epic("5", "E");
        taskManager.saveNewEpic(epic1);
        subTask1.setEpicId(epic1.getId());
        subTask1.setStatus(Status.DONE);
        taskManager.saveNewSubTask(subTask1);

        SubTask exctractedSubTask = taskManager.getSubTask(subTask1.getId());

        Assertions.assertEquals(subTask1, exctractedSubTask);
    }

    @Test
    void checkIfSavesEverythingAsEntered() {
        Task initialTask = new Task("NameNameName", "DescriptionDescriptionDescription");
        taskManager.saveNewTask(initialTask);

        Task exctractedTask = taskManager.getTask(initialTask.getId());
        // у нас нет геттеров на название и описание
        List<Character> initialTaskToString = initialTask.toString().chars().mapToObj(e -> (char) e).toList();
        List<Character> extractedTaskToString = exctractedTask.toString().chars().mapToObj(e -> (char) e).toList();

        Assertions.assertEquals(initialTaskToString, extractedTaskToString);
    }

}