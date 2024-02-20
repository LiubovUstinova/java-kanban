
public class SubTask {
    private Status subTaskStatus;
    private String subTaskDescription;
    private String subTaskName;

    public SubTask(String subTaskName, String subTaskDescription) {
        this.subTaskName = subTaskName;
        this.subTaskStatus = Status.IN_PROGRESS;
        this.subTaskDescription = subTaskDescription;
    }

    public String getSubTaskDescription() {
        return subTaskDescription;
    }

    public void setSubTaskDescription(String subTaskDescription) {
        this.subTaskDescription = subTaskDescription;
    }

    public Status getSubTaskStatus() {
        return subTaskStatus;
    }

    public void setSubTaskStatus(Status subTaskStatus) {
        this.subTaskStatus = subTaskStatus;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                ", subTaskName='" + subTaskName + '\'' +
                ", subTaskDescription='" + subTaskDescription + '\'' +
                "subTaskStatus=" + subTaskStatus +
                '}';
    }
}
