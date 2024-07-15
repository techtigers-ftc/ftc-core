package team.techtigers.core.paths;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.geometry.Point;

/**
 * Represents a waypoint in a trajectory. A waypoint is a point in a trajectory that the robot must
 * pass through.
 */
public class Waypoint {
    private final Pose2d pose;
    private final Vector2d vector;

    /**
     * Constructs a new Waypoint using a pose input
     *
     * @param pose The pose to be saved inside the class
     */
    public Waypoint(Pose2d pose) {
        this.pose = pose;
        vector = new Vector2d(pose.getX(), pose.getY());
    }

    /**
     * Constructs a new Waypoint using x, y, and heading inputs
     *
     * @param x       The x coordinate of the waypoint
     * @param y       The y coordinate of the waypoint
     * @param heading The heading of the waypoint
     */
    public Waypoint(double x, double y, double heading) {
        this(new Pose2d(x, y, heading));
    }

    /**
     * Constructs a new Waypoint using x and y inputs, assuming heading is 0
     *
     * @param x The x coordinate of the waypoint
     * @param y The y coordinate of the waypoint
     */
    public Waypoint(double x, double y) {
        this(new Pose2d(x, y, 0));
    }

    /**
     * Constructs a Waypoint using a Point and a heading
     *
     * @param point   The point of the Waypoint
     */
    public Waypoint(Point point) {
        this(point.x, point.y);
    }


    /**
     * @return the waypoint as a Pose2d
     */
    public Pose2d getPose() {
        return pose;
    }

    /**
     * @return the waypoint as a Vector2d
     */
    public Vector2d getVector() {
        return vector;
    }

    /**
     * @return the x coordinate of the waypoint
     */
    public double getX() {
        return pose.getX();
    }

    /**
     * @return the y coordinate of the waypoint
     */
    public double getY() {
        return pose.getY();
    }

    /**
     * @return the heading of the waypoint
     */
    public double getHeading() {
        return pose.getHeading();
    }

    @Override
    @NonNull
    public String toString() {
        return "x: " + getX() + " y: " + getY() + " h: " + getHeading();
    }
}
