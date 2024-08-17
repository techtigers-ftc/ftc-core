package team.techtigers.core.paths.routeplanning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import team.techtigers.core.paths.ClosestNodeIsTooFarException;
import team.techtigers.core.paths.NodeCannotBeFoundException;
import team.techtigers.core.paths.geometry.Point;
import team.techtigers.core.paths.geometry.Rectangle;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FieldGraphTest {

    private HashMap<Point, FieldNode> map;
    private FieldGraph graph;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
        graph = new FieldGraph(map);
    }

    @Test
    @DisplayName("Test testGetClosestNode_NormalCase")
    void testGetClosestNode_NormalCase() throws NodeCannotBeFoundException, ClosestNodeIsTooFarException {
        FieldNode node1 = new FieldNode(new Rectangle(new Point(10, 10), new Point(0, 0)));
        FieldNode node2 = new FieldNode(new Rectangle(new Point(60, 60), new Point(40, 40)));
        FieldNode node3 = new FieldNode(new Rectangle(new Point(110, 110), new Point(90, 90)));

        map.put(node1.getValue().center, node1);
        map.put(node2.getValue().center, node2);
        map.put(node3.getValue().center, node3);

        Point startPoint = new Point(55, 55);
        double threshold = 24;
        FieldNode closestNode = graph.getClosestNode(startPoint, threshold);
        assertEquals(node2, closestNode);
    }

    @Test
    @DisplayName("Test testGetClosestNode_NodeTooFar")
    void testGetClosestNode_NodeTooFar() {
        // Create a node that is far from the startPoint
        FieldNode node = new FieldNode(new Rectangle(new Point(110, 110), new Point(90, 90)));
        map.put(node.getValue().center, node);

        Point startPoint = new Point(0, 0);
        double threshold = 24;
        assertThrows(ClosestNodeIsTooFarException.class, () -> {
            graph.getClosestNode(startPoint, threshold);
        });
    }

    @Test
    @DisplayName("Test testGetClosestNode_NoNodesInMap")
    void testGetClosestNode_NoNodesInMap() {
        Point startPoint = new Point(0, 0);
        double threshold = 24;
        assertThrows(NodeCannotBeFoundException.class, () -> {
            graph.getClosestNode(startPoint, threshold);
        });
    }
}
