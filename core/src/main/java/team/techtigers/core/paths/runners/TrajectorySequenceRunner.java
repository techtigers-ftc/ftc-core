package team.techtigers.core.paths.runners;

import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.followers.TrajectoryFollower;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.utils.DriveConstants;
import org.firstinspires.ftc.teamcode.utils.RobotState;

/**
 * Follows a TrajectorySequence.
 * <p>
 * Copied from roadrunner quickstart.
 * </p>
 */
public class TrajectorySequenceRunner implements IRunner {
    private final TrajectoryFollower follower;


    private final RobotState state;
    private Trajectory currentTrajectory;
    private Pose2d lastPoseError = new Pose2d();


    public TrajectorySequenceRunner(
            TrajectoryFollower follower, RobotState state
    ) {
        this.follower = follower;

        this.state = state;
    }

    public void setTrajectory(Trajectory trajectory) {
        currentTrajectory = trajectory;
    }


    @Override
    public void start() {
        follower.followTrajectory(currentTrajectory);
    }

    @Override
    public @Nullable
    DriveSignal update() {
        DriveSignal driveSignal;

        if (currentTrajectory == null)
            return new DriveSignal();

        if (!follower.isFollowing()) {
            currentTrajectory = null;

            driveSignal = new DriveSignal();
        } else {
            driveSignal = follower.update(state.getRobotCurrentPose(), state.getRobotVelocity());
            lastPoseError = follower.getLastError();
        }

        final double NOMINAL_VOLTAGE = 12.0;
        double voltage = state.getBatteryVoltage();
        if (driveSignal != null && !DriveConstants.RUN_USING_ENCODER && voltage != 0) {
            driveSignal = new DriveSignal(
                    driveSignal.getVel().times(NOMINAL_VOLTAGE / voltage),
                    driveSignal.getAccel().times(NOMINAL_VOLTAGE / voltage)
            );
        }

        return driveSignal;
    }

    public Pose2d getLastPoseError() {
        return lastPoseError;
    }

    @Override
    public boolean isFinished() {
        return currentTrajectory == null;
    }

    @Override
    public boolean runWithFeedforwardVelocity() {
        return true;
    }

    public Pose2d getExpectedPose() {
        if (currentTrajectory != null) {
            return currentTrajectory.get(follower.elapsedTime());
        }
        return new Pose2d();
    }
}
