package tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Status;

class TaskTest {

    @Test
    void equals_checkIfTasksEqualById() {
        Task task1 = new Task("A", "B");
        Task task2 = new Task("C", "D");
        task1.setId(1);
        task2.setId(1);
        task1.setStatus(Status.NEW);
        task2.setStatus(Status.DONE);

        Assertions.assertEquals(task1, task2);

    }

    @Test
    void equals_checkIfEpicsEqualById() {
        Epic epic1 = new Epic("E", "F");
        Epic epic2 = new Epic("G", "H");
        epic1.setId(2);
        epic2.setId(2);
        epic1.setStatus(Status.NEW);
        epic2.setStatus(Status.DONE);

        Assertions.assertEquals(epic1, epic2);

    }

    @Test
    void equals_checkIfSubTasksEqualById() {
        SubTask subTask1 = new SubTask("I", "J");
        SubTask subTask2 = new SubTask("K", "Q");
        subTask1.setId(3);
        subTask2.setId(3);
        subTask1.setStatus(Status.NEW);
        subTask2.setStatus(Status.DONE);

        Assertions.assertEquals(subTask1, subTask2);
    }
}