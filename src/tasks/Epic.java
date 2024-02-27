package tasks;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Integer> subTasksId;

    public Epic(String epicTaskName, String epicTaskDescription) {
        super(epicTaskName, epicTaskDescription);
        subTasksId = new ArrayList<>();
    }

    public void addNewSubTaskId(int subTaskId) {
        subTasksId.add(subTaskId);
    }

    public ArrayList<Integer> getSubTasksId() {
        return subTasksId;
    }

    @Override
    public String toString() {
        return "Глобальная задача: {" +
                " Имя: " + taskName +
                ", описание: " + taskDescription +
                ", id = " + id +
                ", статус: " + status +
                '}';
    }
}
