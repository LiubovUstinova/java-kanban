import java.util.ArrayList;

public class Task {
    private String taskName;
    private String description;
    private final int NUMBER_ID;
    private static int totalTasksCreated = 0;
    private Status status;
    private final ArrayList<SubTask> SUB_TASKS;

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        totalTasksCreated++;
        NUMBER_ID = totalTasksCreated;
        status = Status.NEW;
        SUB_TASKS = new ArrayList<>();
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public int getNUMBER_ID() {
        return NUMBER_ID;
    }

    public Status getStatus() {
        return status;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String addNewSubTask(String subTaskName, String subTaskDescription) {
        SUB_TASKS.add(new SubTask(subTaskName, subTaskDescription));
        if (!status.equals(Status.IN_PROGRESS)) {
            status = Status.IN_PROGRESS;
        }
        return "OK";
    }

    public String updateSubTaskStatus(String subTaskName) {
        for (SubTask subTask : SUB_TASKS) {
            if (subTaskName.equals(subTask.getSubTaskName())) {
                subTask.setSubTaskStatus(Status.DONE);
                return checkTaskStatusOnUpdate();
            }
        }
        return "Error. SubTask not found;";
    }

    public void showSubTasks() {
        for (SubTask subTask : SUB_TASKS) {
            System.out.println(subTask);
        }
    }

    private String checkTaskStatusOnUpdate() {
        for (SubTask subTask : SUB_TASKS) {
            if (!subTask.getSubTaskStatus().equals(Status.DONE)) {
                return "Статус действия (subTask) изменён. Задача (Task) находится в статусе IN_PROGRESS.";
            }
        }
        status = Status.DONE;
        return "Статус действия (subTask) изменён. Все действия (subTask's) выполнены. " +
                "Статус задачи (Task) изменён на DONE (выполнено).";
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", NUMBER_ID=" + NUMBER_ID +
                ", status=" + status +
                '}';
    }
}
