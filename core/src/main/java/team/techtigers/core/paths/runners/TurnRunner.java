package team.techtigers.core.paths.runners;

import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.controller.PIDController;

import org.firstinspires.ftc.teamcode.utils.RobotState;

/**
 * A runner that turns the robot to a certain heading
 */
public class TurnRunner implements IRunner {
    private static final PIDCoefficients HEADING_PID = new PIDCoefficients(140, 0, 0);
    private final PIDController headingController;
    private final double targetHeading;
    private final RobotState robotState;

    /**
     * Creates a new TurnRunner object
     *
     * @param robotState    the robot state, used to get the robot heading
     * @param targetHeading the target heading to turn to
     */
    public TurnRunner(RobotState robotState, double targetHeading) {
        this.robotState = robotState;
        this.targetHeading = targetHeading;
        headingController = new PIDController(HEADING_PID.kP, HEADING_PID.kI, HEADING_PID.kD);
    }

    @Override
    public void start() {
        headingController.setSetPoint(targetHeading);

        headingController.setTolerance(3);
        headingController.reset();
        headingController.calculate();
    }

    @Nullable
    @Override
    public DriveSignal update() {
        double headingCorrection =
                headingController.calculate(Math.toDegrees(robotState.getRobotCurrentPose().getHeading()));
        return new DriveSignal(new Pose2d(0, 0, headingCorrection), new Pose2d());
    }

    @Override
    public boolean isFinished() {
        return headingController.atSetPoint();
    }

    @Override
    public boolean runWithFeedforwardVelocity() {
        return false;
    }
}
