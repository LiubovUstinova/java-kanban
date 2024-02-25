package utils;

public class IdGenerator {
    private static int taskId = 0;
    private static int epicId = 0;
    private static int subTaskId = 0;

    public static int generateTaskId() {
        taskId++;
        return taskId;
    }

    public static int generateEpicId() {
        epicId++;
        return epicId;
    }

    public static int generateSubTaskId() {
        subTaskId++;
        return subTaskId;
    }
}
