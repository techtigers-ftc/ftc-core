package team.techtigers.core.actions;

/**
 * Interface for an action.
 * Will be able to create a step for a manipulator to use with either a servo or a motor
 *
 * <p> <b>Episode III</b>: The revenge of the Servo Actions </p>
 */
public interface IAction {
    /**
     * Updates the values of the motor or servo
     */
    void update();

    /**
     * Returns whether the motor or servo is done with the action
     * @return if the servo has finished going to its position
     */
    boolean isFinished();

    /**
     * Allows the motor or servo to initialize its values before doing any update calculations
     */
    void start();
}