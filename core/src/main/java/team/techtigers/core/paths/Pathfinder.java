package team.techtigers.core.paths;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.paths.routeplanning.Dijkstra;
import org.firstinspires.ftc.teamcode.paths.routeplanning.FieldGraph;
import org.firstinspires.ftc.teamcode.paths.routeplanning.FieldNode;
import org.firstinspires.ftc.teamcode.paths.routeplanning.GraphBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Returns a trajectory sequence given a starting and ending position, accounting for any known
 * obstacles on the field.
 */
public class Pathfinder {
    private static final double BACK_ANGLE_TOLERANCE = Math.toRadians(45);
    private static Pathfinder instance;
    private final FieldGraph graph;

    private final Dijkstra planner;


    /**
     * Construct a Pathfinder
     */
    private Pathfinder() {
        graph = GraphBuilder.buildGraph();
        planner = new Dijkstra<>();
    }

    /**
     * Initialize the Pathfinder instance
     */
    public static void initialize() {
        instance = new Pathfinder();
    }

    /**
     * Get the Pathfinder instance, given that it has been initialized
     *
     * @return The Pathfinder instance
     */
    public static Pathfinder getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Pathfinder has not been initialized");
        }
        return instance;
    }

    private static double getAngleBetween3Points(Waypoint first, Waypoint second, Waypoint third) {
        Vector2d firstVector = first.getVector();
        Vector2d secondVector = second.getVector();
        Vector2d thirdVector = third.getVector();

        Vector2d secondToFirst = firstVector.minus(secondVector);
        Vector2d secondToThird = thirdVector.minus(secondVector);

        return secondToFirst.angleBetween(secondToThird);
    }

    private boolean poseCloseEnough(Pose2d pose1, Pose2d pose2) {
        double tolerance = 2.5;
        boolean xEquals = Math.abs(pose1.getX() - pose2.getX()) < tolerance;
        boolean yEquals = Math.abs(pose1.getY() - pose2.getY()) < tolerance;
        boolean headingEquals = Math.abs(pose1.getHeading() - pose2.getHeading()) < tolerance;

        return xEquals && yEquals && headingEquals;
    }

    private ArrayList<Waypoint> pruneNodes(ArrayList<Waypoint> nodes) {
        ArrayList<Waypoint> newList = new ArrayList<>();
        ArrayList<Waypoint> queue = new ArrayList<>();

        for (int index = 0; index < nodes.size(); index++) {
            if (queue.size() < 3) {
                queue.add(nodes.get(index));
            }
            if (queue.size() == 3) {
                double angle = getAngleBetween3Points(queue.get(0), queue.get(1), queue.get(2));
                double angleDelta = Math.abs(Math.toRadians(180) - angle);
                boolean removeNode = angleDelta < 0.001;
                if (removeNode) {
                    queue.remove(1);
                } else {
                    newList.add(queue.get(0));
                    queue.remove(0);
                }
            }
        }

        newList.addAll(queue);

        return newList;
    }

    /**
     * Generate a TrajectorySequence from any pose to another
     *
     * @param startPose The starting pose
     * @param endPose   The ending Pose
     * @return Automatically generate paths in the Auto-Op, or create trajectories in the Tele-Op
     * in order to make controlling the robot easier
     */
    public Trajectory generatePath(Pose2d startPose, Pose2d endPose) throws ClosestNodeIsTooFarException, NodeCannotBeFoundException, PathCannotBeFoundException, TrajectoryFactoryException {
        if (poseCloseEnough(startPose, endPose)) {
            throw new PathCannotBeFoundException();
        }

        graph.reset();

        FieldNode start = graph.getClosestNode(startPose);
        FieldNode end = graph.getClosestNode(endPose);

        if (start.getValue().center.equals(end.getValue().center)) {
            return TrajectoryFactory.createFromWaypoints(
                    new ArrayList<>(
                            Arrays.asList(new Waypoint[]{
                                    new Waypoint(startPose), new Waypoint(endPose)
                            })));
        }

        ArrayList<FieldNode> nodePath = planner.findPath(start, end);

        ArrayList<Waypoint> path = (ArrayList<Waypoint>)
                nodePath.stream().map((fieldNode -> new Waypoint(fieldNode.getValue().center)))
                        .collect(Collectors.toList());

        double headingDiff = (endPose.getHeading() - startPose.getHeading()) / (path.size() - 1);
        for (int index = 0; index < path.size(); index++) {
            Waypoint currentWaypoint = path.get(index);
            path.set(index, new Waypoint(currentWaypoint.getX(), currentWaypoint.getY(),
                    startPose.getHeading() + (index * headingDiff)));
        }

        if (path.size() == 0) {
            throw new PathCannotBeFoundException();
        }

        // If start pose and start node center pose is the same, do not add the start pose point
        if (!poseCloseEnough(startPose, path.get(0).getPose())) {
            path.add(0, new Waypoint(startPose));
        }

        // If end pose and end node center pose is the same, do not add the end pose point
        if (!poseCloseEnough(endPose, path.get(path.size() - 1).getPose())) {
            path.add(new Waypoint(endPose));
        }

        // Removing back and forth movement that stalls calculation in beginning
        if (path.size() > 2) {
            double startAngle = getAngleBetween3Points(path.get(0),
                    path.get(1), path.get(2));
            if (Math.abs(startAngle) < BACK_ANGLE_TOLERANCE) {
                path.remove(1);
            }
        }

        // Removing back and forth movement that stalls calculation at end
        if (path.size() > 2) {
            double endAngle = getAngleBetween3Points(path.get(path.size() - 3),
                    path.get(path.size() - 2),
                    path.get(path.size() - 1));
            if (Math.abs(endAngle) < BACK_ANGLE_TOLERANCE) {
                path.remove(path.size() - 2);
            }
        }

        RobotLog.vv("tt-sm", "Before PRUNE");
        for (Waypoint point: path) {
            RobotLog.vv("tt-sm", "%s", point);
        }

        path = pruneNodes(path);

        RobotLog.vv("tt-sm", "AFTER PRUNE");
        for (Waypoint point: path) {
            RobotLog.vv("tt-sm", "%s", point);
        }

        return TrajectoryFactory.createFromWaypoints(path);
    }
}
