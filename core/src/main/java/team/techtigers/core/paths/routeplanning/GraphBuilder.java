package team.techtigers.core.paths.routeplanning;


import java.util.HashMap;

import team.techtigers.core.paths.geometry.Point;
import team.techtigers.core.paths.geometry.Rectangle;

/**
 * The class used to create graphs
 */
public class GraphBuilder {
//    private static final int[][] MAP = new int[][]{
//            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//            new int[]{1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
//            new int[]{1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1},
//            new int[]{0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
//            new int[]{0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
//            new int[]{0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
//            new int[]{0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
//            new int[]{1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
//            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//    };

    private static final int[][] MAP = new int[][] {
            new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
            new int[] { 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0 },
            new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            new int[] { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 },
            new int[] { 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0 },
            new int[] { 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0 },
            new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            new int[] { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    private static final int DIVISIONS_PER_TILE = MAP.length / 8;

    /**
     * Build a graph using the 36 tiles on the field. It is very specific to the CenterStage FTC season
     *
     * @return The graph
     */
    public static FieldGraph buildGraph() {

        HashMap<Point, FieldNode> map = new HashMap<>();
        for (int x = 0; x < MAP.length; x++) {
            for (int y = 0; y < MAP[x].length; y++) {
                Point bottomRight = new Point(MAP.length - 1 - x, MAP[x].length - 1 - y);
                boolean isIncluded = MAP[x][y] != 0;
                FieldNode node = new FieldNode(new Rectangle(
                        new Point(bottomRight.x + 1, bottomRight.y),
                        new Point(bottomRight.x, bottomRight.y + 1)
                ));
                if (isIncluded) {
                    map.put(bottomRight, node);
                }
            }
        }

        for (Point point : map.keySet()) {
            FieldNode current = map.get(point);
            for (int dx = -1; dx < 2; dx++) {
                for (int dy = -1; dy < 2; dy++) {
                    Point neighbor = point.add(new Point(dx, dy));
                    if (map.containsKey(neighbor)) {
                        map.get(neighbor).addConnection(new Connection(current, point.dist(neighbor)));
                    }
                }
            }
        }

        for (Point k : map.keySet()) {
            FieldNode n = map.get(k);
            // This will not work for non-square maps.
            n.getValue().scale(24.0 / DIVISIONS_PER_TILE);
        }
        return new FieldGraph(map);
    }
}
