package tasks;

public class Epic extends Task {

    public Epic(String epicTaskName, String epicTaskDescription) {
        super(epicTaskName, epicTaskDescription);
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
