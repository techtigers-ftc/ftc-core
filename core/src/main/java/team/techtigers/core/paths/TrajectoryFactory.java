package team.techtigers.core.paths;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.EmptyPathSegmentException;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;

import org.firstinspires.ftc.teamcode.utils.DriveConstants;

import java.util.ArrayList;

/**
 * A factory class for creating trajectories.
 */
public class TrajectoryFactory {

    private static double calculateTangent(Waypoint startPoint, Waypoint endPoint) {
        Vector2d vector = endPoint.getVector().minus(startPoint.getVector());
        return vector.angle();
    }

    /**
     * Creates a trajectory sequence from a list of waypoints.
     *
     * @param waypointsList The waypoints to be used.
     * @return The trajectory sequence.
     */
    public static Trajectory createFromWaypoints(ArrayList<Waypoint> waypointsList) throws PathCannotBeFoundException, TrajectoryFactoryException {
        // Check if there are enough waypoints
        if (waypointsList.size() < 2) {
            throw new PathCannotBeFoundException();
        }

        // Create trajectory builder with start tangent
        double startTangent = calculateTangent(waypointsList.get(0), waypointsList.get(1));

        TrajectoryBuilder builder = new TrajectoryBuilder(
                waypointsList.get(0).getPose(),
                startTangent,
                DriveConstants.VEL_CONSTRAINT,
                DriveConstants.ACCEL_CONSTRAINT
        );

        // Intermediate Points. Calculates tangent between last point and the next point.
        for (int index = 1; index <= waypointsList.size() - 2; index++) {
            Waypoint currentWaypoint = waypointsList.get(index);
            Waypoint lastWaypoint = waypointsList.get(index - 1);
            Waypoint nextWaypoint = waypointsList.get(index + 1);

            addWaypoint(builder, getTrajectoryType(lastWaypoint, currentWaypoint), currentWaypoint,
                    calculateTangent(lastWaypoint, nextWaypoint));
        }

        // This is the end of the spline, tangent is the same as the last point's heading
        Waypoint finalWaypoint = waypointsList.get(waypointsList.size() - 1);

        int trajectoryType = getTrajectoryType(waypointsList.get(waypointsList.size() - 2),
                finalWaypoint);

        if (waypointsList.size() == 2 && trajectoryType != 0) {
            trajectoryType = 2;
        }

        addWaypoint(builder, trajectoryType, finalWaypoint, finalWaypoint.getHeading());

        return builder.build();
    }

    private static int getTrajectoryType(Waypoint first, Waypoint second) {
        // Calculate if constant heading spline is needed
        double headingDiff = Math.abs(second.getHeading() - first.getHeading());
        // Converts to -PI to PI range (shortest angle)
        headingDiff = (headingDiff + Math.PI) % (2 * Math.PI) - Math.PI;

        // Determines what type of trajectory to run
        // For no heading diff, use constant heading spline
        // For 2 waypoints and heading diff, use linear heading spline
        // For 3+ waypoints and heading diff, use spline heading spline
        return Math.abs(headingDiff) < Math.toRadians(1) ? 0 : 1;
    }

    private static void addWaypoint(TrajectoryBuilder builder,
                                    int trajectoryType,
                                    Waypoint currentWaypoint,
                                    double tangent) throws TrajectoryFactoryException {
        try {
            if (trajectoryType == 0) {
                builder.splineToConstantHeading(currentWaypoint.getVector(),
                        tangent);
            } else if (trajectoryType == 1) {
                builder.splineToSplineHeading(currentWaypoint.getPose(),
                        tangent);
            } else {
                builder.splineToLinearHeading(currentWaypoint.getPose(),
                        tangent);
            }
        } catch (EmptyPathSegmentException e) {
            throw new TrajectoryFactoryException();
        }
    }
}
