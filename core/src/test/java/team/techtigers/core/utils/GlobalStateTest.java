package team.techtigers.core.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GlobalStateTest {
    @Nested
    @DisplayName("startTimer()")
    class StartTimerMethod {
        @Test
        @DisplayName("Starts a timer")
        public void testStartTimer() throws InterruptedException{
            GlobalState globalState = new GlobalState();
            Thread.sleep(1);
            double runTime = globalState.getRunTime();
            assertTrue(runTime > 0);
        }
    }
}