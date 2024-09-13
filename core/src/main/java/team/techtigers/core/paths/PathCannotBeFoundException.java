package team.techtigers.core.paths;

/**
 * Error condition where pathfinder cannot find a trajectory
 */
public class PathCannotBeFoundException extends Exception {
    /**
     * Construct a PathCannotBeFoundException
     */
    public PathCannotBeFoundException() {
        super();
    }
}
