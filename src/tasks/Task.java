package tasks;

import utils.Status;

import java.util.Objects;

public class Task {
    protected String taskName;
    protected String taskDescription;
    protected Integer id;
    protected Status status;

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
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
