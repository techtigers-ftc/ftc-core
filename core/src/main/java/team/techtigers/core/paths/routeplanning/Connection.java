package team.techtigers.core.paths.routeplanning;

/**
 * A connection to another Node
 */
public class Connection {
    /**
     * The target Node
     */
    public final Node target;

    /**
     * The cost of traversal
     */
    public final double cost;

    /**
     * Construct a Connection
     *
     * @param target The target
     * @param cost   The cost of traversal
     */
    public Connection(Node target, double cost) {
        this.target = target;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.target.toString();
    }
}
