package tasks;

public class SubTask extends Task {
    private Integer epicId;

    public SubTask(String subTaskName, String subTaskDescription) {
        super(subTaskName, subTaskDescription);
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Действие: {" +
                " Имя: " + taskName +
                ", описание: " + taskDescription +
                ", id действия = " + id +
                ", id связанной глобальной задачи = " + epicId +
                ", статус: " + status +
                '}';
    }
}
