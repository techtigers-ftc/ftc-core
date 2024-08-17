package team.techtigers.core.paths.routeplanning;


import java.util.HashMap;

import team.techtigers.core.paths.geometry.Point;
import team.techtigers.core.paths.geometry.Rectangle;

/**
 * The class used to create graphs representing the game field
 */
public class GraphBuilder {

    /**
     * Build a graph using the 36 tiles on the field, the provided map, and the number of divisions per tile.
     * @param fieldMap A square 2D array with only 1s and 0s, with each value representing whether or not the
     *                 robot is allowed to travel to that coordinate. Use this field to block out known obstacles.
     * @param divisionsPerTile Used to scale the coordinates of the final graph. The number of
     *                         divisions per each field tile used.
     * @return A graph representing the field given
     */
    public static FieldGraph buildGraph(int[][] fieldMap, double divisionsPerTile) {

        HashMap<Point, FieldNode> map = new HashMap<>();
        for (int x = 0; x < fieldMap.length; x++) {
            for (int y = 0; y < fieldMap[x].length; y++) {
                Point bottomRight = new Point(fieldMap.length - 1 - x, fieldMap[x].length - 1 - y);
                boolean isIncluded = fieldMap[x][y] != 0;
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
            n.getValue().scale(24.0 / divisionsPerTile);
        }
        return new FieldGraph(map);
    }
}
