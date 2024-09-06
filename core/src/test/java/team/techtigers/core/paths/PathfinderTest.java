package team.techtigers.core.paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PathfinderTest {

    private Pathfinder pathfinder;

    @BeforeEach
    void setUp() {
         int[][] map = new int[][] {
                 new int[] { 1, 0, 0, 0, 0, 0 },
                 new int[] { 1, 0, 0, 0, 0, 0 },
                 new int[] { 1, 0, 0, 1, 0, 0 },
                 new int[] { 1, 0, 1, 1, 0, 1 },
                 new int[] { 1, 1, 0, 0, 0, 0 },
                 new int[] { 1, 0, 0, 0, 0, 0 },
         };
        double divisionsPerTile = 1;
        Pathfinder.initialize(map, divisionsPerTile);
        pathfinder = Pathfinder.getInstance();
    }

    @Test
    @DisplayName("Test Pathfinder Init")
    void testInitialization() {
        assertNotNull(pathfinder, "Pathfinder should be initialized.");
    }

    @Test
    @DisplayName("Test Pathfinder")
    void testGeneratePath() throws ClosestNodeIsTooFarException, NodeCannotBeFoundException, PathCannotBeFoundException {
        Waypoint startPoint = new Waypoint(-12, -12, 0);
        Waypoint endPoint1 = new Waypoint(12, 12, 0);
        Waypoint endPoint2 = new Waypoint(-60, 60, 0);

        ArrayList<Waypoint> path1 = pathfinder.generatePath(startPoint, endPoint1);
        ArrayList<Waypoint> path2 = pathfinder.generatePath(startPoint, endPoint2);

        assertEquals(2, path1.size(), "Path should contain 2 waypoints.");

        assertEquals(4, path2.size(), "Path should contain 4 waypoints.");
    }
}