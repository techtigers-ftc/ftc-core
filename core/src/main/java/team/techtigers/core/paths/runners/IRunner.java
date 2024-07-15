package team.techtigers.core.paths.runners;

import com.acmerobotics.roadrunner.drive.DriveSignal;

/**
 * Interface for a runner, used to calculate values for a certain motion
 */
public interface IRunner {
    /**
     * Starts the runner calculations
     */
    void start();

    /**
     * Updates the runner calculations
     * @return the drive signal to be sent to the drive subsystem containing the velocity and
     * acceleration
     */
    DriveSignal update();

    /**
     * Checks if the runner is finished
     * @return true if the runner is finished, false otherwise
     */
    boolean isFinished();

    /**
     * Return true if DriveSignal wants feedforward velocity
     *
     * @return true if DriveSignal wants to run with feedforward velocity
     */
    boolean runWithFeedforwardVelocity();
}
