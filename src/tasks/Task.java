package tasks;

import utils.Status;

public class Task {
    protected String taskName;
    protected String taskDescription;
    protected Integer id;
    protected Status status;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return "Задача: {" +
                " Имя: " + taskName +
                ", описание: " + taskDescription +
                ", id = " + id +
                ", статус: " + status +
                '}';
    }
}
