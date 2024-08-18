package team.techtigers.core.utils;

import java.io.Serializable;

/**
 * A class to store information that is global to the entire robot
 */
public class GlobalState implements Serializable {
    public long startTime;

    /**
     * Initializes a new GlobalState with a timer
     */
    public GlobalState() {
        startTime = System.currentTimeMillis();
    }

    public long getRunTime() {
        return System.currentTimeMillis() - startTime;
    }
}
