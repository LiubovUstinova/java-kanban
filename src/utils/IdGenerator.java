package utils;

public class IdGenerator {
    private static int taskId = 0;

    public static int generateTaskId() {
        taskId++;
        return taskId;
    }
}
