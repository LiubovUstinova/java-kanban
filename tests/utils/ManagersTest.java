package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagersTest {

    @Test
    void getDefaultTaskManagerTest() {
        Assertions.assertNotNull(Managers.getDefaultTaskManager());
    }
}
