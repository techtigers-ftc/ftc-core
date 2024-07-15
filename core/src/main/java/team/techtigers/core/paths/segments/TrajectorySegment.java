package team.techtigers.core.paths.segments;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import java.util.Collections;

/**
 * Represents a drive trajectory segment.
 * <p>
 *     Copied from roadrunner quickstart.
 * </p>
 */
public final class TrajectorySegment extends SequenceSegment {
    private final Trajectory trajectory;

    public TrajectorySegment(Trajectory trajectory) {
        // Note: Markers are already stored in the `Trajectory` itself.
        // This class should not hold any markers
        super(trajectory.duration(), trajectory.start(), trajectory.end(), Collections.emptyList());
        this.trajectory = trajectory;
    }

    public Trajectory getTrajectory() {
        return this.trajectory;
    }
}
