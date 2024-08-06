package team.techtigers.core;

import java.util.HashMap;

/**
 * Saves any value from one opmode to be used in another
 */
public class RobotSaveState {
    private HashMap<String, Object> state;
    private static RobotSaveState instance;

    private RobotSaveState(){
        state = new HashMap<>();
    }

    /**
     * If there is an instance of the class, returns it
     * Otherwise, creates a new instance and returns it
     * @return The instance of the class
     */
    public static RobotSaveState getInstance() {
        if (instance == null) {
            instance = new RobotSaveState();
        }

        return instance;
    }

    /**
     * Gets the current robot state
     * @return The current robot state
     */
    public HashMap<String, Object> getState() {
        try {
            return state;
        } catch (NullPointerException e){
            System.out.println("Error: No state has been set");
            System.out.println("Returning null instead");
            return null;
        }
    }

    /**
     * Sets the current robot state, requires a string for identification
     * and object for the value to be saved
     * @param state The new value to be saved
     */
    public void setState(HashMap<String, Object> state) {
        this.state = state;
    }

    /**
     * Resets the state to be null
     */
    public void reset() {
        state = null;
    }
}