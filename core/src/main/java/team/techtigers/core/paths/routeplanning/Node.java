package team.techtigers.core.paths.routeplanning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A Node in a graph
 *
 * @param <T> The type of a custom property that can be used to associate an abstract node
 *            with a real value. You can use this to store relevant data inside a node.
 */
public class Node<T> {
    private final ArrayList<Connection> connections;
    private double cost;
    private Node<T> source;
    private final String id;
    private boolean traversed = false;

    private T value;

    /**
     * Construct a Node
     *
     * @param connections The connections to the Node
     * @param id          An ID you can associate with the Node for debugging purposes
     */
    public Node(ArrayList<Connection> connections, String id) {
        this.connections = connections;
        this.id = id;
        reset();
    }

    /**
     * Construct a Node
     *
     * @param connections The connections to the Node
     * @param value       A value associated with the Node
     */
    public Node(ArrayList<Connection> connections, T value) {
        this(connections, value.toString());
        this.value = value;
    }

    /**
     * Construct a Node
     * The connections will be an empty ArrayList
     *
     * @param value A value associated with the Node
     */
    public Node(T value) {
        this(new ArrayList<>(), value);
    }

    /**
     * Update the source and cost
     *
     * @param source The source Node
     * @param cost   The new cost
     */
    public void update(Node<T> source, double cost) {
        if (cost < this.cost) {
            this.cost = cost;
            this.source = source;
        }
    }

    /**
     * Update all of the connected Nodes
     *
     * @return An iterator (immutable) of previously not traversed Nodes
     */
    public Iterator<Node> traverseConnections() {
        this.traversed = true;
        return connections.stream().map((conn) ->
        {
            conn.target.update(this, this.cost + conn.cost);
            return conn.target;
        }).filter(Node::hasNotBeenTraversed).iterator();
    }

    /**
     * @return Whether this Node has been traversed
     */
    private boolean hasNotBeenTraversed() {
        return !this.traversed;
    }

    /**
     * @return The source Node
     */
    public Node<T> getSource() {
        return this.source;
    }

    /**
     * @return The cost
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Reset the Node to the original state
     */
    public void reset() {
        cost = Double.POSITIVE_INFINITY;
        source = null;
        traversed = false;
    }

    /**
     * Get the Node's value
     *
     * @return The value
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Add multiple connections
     *
     * @param conns The connections
     */
    public void addConnections(List<Connection> conns) {
        connections.addAll(conns);
    }

    /**
     * Add one connection
     *
     * @param conn The connection
     */
    public void addConnection(Connection conn) {
        connections.add(conn);
    }

    /**
     * Get the connections
     * @return The connections
     */
    public ArrayList<Connection> getConnections() {
        return this.connections;
    }

    /**
     * Convert this Node to string
     *
     * @return The value's string representation
     */
    @Override
    public String toString() {
        return this.getValue().toString();
    }

    /**
     * Set the Node's cost
     *
     * @param cost The Cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Print out the node for debugging purposes
     */
    public void dump() {
    }
}

