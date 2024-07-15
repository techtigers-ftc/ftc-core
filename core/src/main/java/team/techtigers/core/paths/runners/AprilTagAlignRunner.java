package team.techtigers.core.paths.runners;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.utils.RobotState;
import org.firstinspires.ftc.teamcode.vision.PersistentAprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

/**
 * A runner that aligns the robot to an april tag
 */
public class AprilTagAlignRunner implements IRunner {
    private static final double YAW_TOLERANCE = 3;
    private static final double RANGE_TOLERANCE = 2;
    private static final double BEARING_TOLERANCE = 2;
    private static final double TARGET_DISTANCE = 13;
    private static final double TARGET_BEARING = 0.0;
    private static final double TARGET_YAW = 0.0;
    private final int tagId;
    private final double MAX_AUTO_SPEED = 0.5;
    private final double MAX_AUTO_STRAFE = 0.5;
    private final double MAX_AUTO_TURN = 0.3;
    private final RobotState robotState;
    private final PIDFController yawController;
    private final PIDFController rangeController;
    private final PIDFController bearingController;
    private boolean isFinished;
    private int waitCounter = 0;

    /**
     * Creates a new AprilTagAlignRunner object
     * @param robotState the robot state, used to get the april tag values
     * @param tagId the id of the april tag to align to
     */
    public AprilTagAlignRunner(RobotState robotState, int tagId) {
        this.robotState = robotState;
        this.tagId = tagId;

        rangeController = new PIDFController(new PIDCoefficients(0.03, 0, 0));
        rangeController.setOutputBounds(-MAX_AUTO_SPEED, MAX_AUTO_SPEED);
        bearingController = new PIDFController(new PIDCoefficients(-0.015 * 1.9, -0.01, 0.0003));
        bearingController.setOutputBounds(-MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
        yawController = new PIDFController(new PIDCoefficients(0.015 * 0.3 * 0.5, 0, 0));
        yawController.setOutputBounds(-MAX_AUTO_TURN, MAX_AUTO_TURN);

        isFinished = false;
    }

    @Override
    public void start() {
        yawController.reset();
        rangeController.reset();
        bearingController.reset();

        yawController.setTargetPosition(TARGET_YAW);
        rangeController.setTargetPosition(TARGET_DISTANCE);
        bearingController.setTargetPosition(TARGET_BEARING);

        isFinished = false;
        waitCounter = 0;
    }

    @Override
    public DriveSignal update() {
        PersistentAprilTagDetection detection = robotState.getAprilTagDetection(tagId);

        if (detection.isExpired()) {
            waitCounter++;
            if (waitCounter > 100) {
                isFinished = true;
            }
        } else {
            waitCounter = 0;
            AprilTagPoseFtc targetPose = detection.getPose();
            double currentRange = targetPose.range;
            double rangePower = -rangeController.update(currentRange);
            double rangeDelta = Math.abs(rangeController.getLastError());

            double currentBearing = targetPose.bearing;
            double bearingPower = -bearingController.update(currentBearing);
            double bearingDelta = Math.abs(bearingController.getLastError());

            double currentYaw = targetPose.yaw;
            double yawPower = -yawController.update(currentYaw);
            double yawDelta = Math.abs(yawController.getLastError());

            isFinished = bearingDelta < BEARING_TOLERANCE && rangeDelta < RANGE_TOLERANCE && yawDelta < YAW_TOLERANCE;
            return new DriveSignal(new Pose2d(rangePower,
                    bearingPower,
                    yawPower), new Pose2d());
        }

        return new DriveSignal();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean runWithFeedforwardVelocity() {
        return false;
    }
}
