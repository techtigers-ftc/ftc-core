package team.techtigers.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class RobotSaveStateTest {
    @BeforeEach
    public void resetSingleton() {
        RobotSaveState.reset();
    }
    @Nested
    @DisplayName("Set state")
    class SetStateMethod {
        @Test
        @DisplayName("Setting a state")
        public void testSetState() {
            RobotSaveState instance = RobotSaveState.getInstance();
            instance.setState("key", "value");
            assertEquals("value", instance.getState("key"));
        }

        @Test
        @DisplayName("Getting a state with a non-existent key")
        public void gettingStateWithNonExistentKey() {
            RobotSaveState instance = RobotSaveState.getInstance();
            assertThrows(IllegalStateException.class, () -> instance.getState("key"));
        }

        @Test
        @DisplayName("Setting a state with null key")
        public void settingStateWithNullKey() {
            RobotSaveState instance = RobotSaveState.getInstance();
            assertThrows(NullPointerException.class, () -> instance.setState(null, "value"));
        }

        @Test
        @DisplayName("Setting a state with null value")
        public void settingStateWithNullValue() {
            RobotSaveState instance = RobotSaveState.getInstance();
            instance.setState("key", null);
            assertNull(instance.getState("key"));
        }

        @Test
        @DisplayName("Resetting the instance")
        public void resettingInstance() {
            RobotSaveState instance = RobotSaveState.getInstance();
            instance.setState("key", "value");
            RobotSaveState.reset();
            RobotSaveState newInstance = RobotSaveState.getInstance();
            assertThrows(IllegalStateException.class, () -> newInstance.getState("key"));
        }
    }
}