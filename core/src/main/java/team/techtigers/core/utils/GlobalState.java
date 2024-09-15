package team.techtigers.core.utils;

import java.io.Serializable;

/**
 * A class to store information that is global to the entire robot. This is
 * intended to be extended from, and child classes can add in additional
 * attributes that are desired in the state.
 */
public class GlobalState implements Serializable {
    /**
     * The time that the timer was started
     */
    private long startTime;

    /**
     * Initializes a new GlobalState with a timer
     */
    public GlobalState() {
        resetTimer();
    }

    /**
     * Returns the amount of time elapsed since the last reset
     *
     * @return the amount of time elapsed since the last reset
     */
    public long getRunTime() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Resets the timer running in GlobalState
     */
    public void resetTimer() {
        startTime = System.currentTimeMillis();
    }
}
