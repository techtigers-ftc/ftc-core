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
     * A standard field goes from -72 to 72
     *
     * @param fieldMap         A square 2D array with only 1s and 0s, with each value representing whether or not the
     *                         robot is allowed to travel to that coordinate. Use this field to block out known obstacles.
     * @param divisionsPerTile Used to scale the coordinates of the final graph. The number of
     *                         divisions in one direction per each field tile
     *                         used.
     * @return A graph representing the field given
     */
    public static FieldGraph buildGraph(int[][] fieldMap, double divisionsPerTile) {

        HashMap<Point, FieldNode> map = new HashMap<>();
        for (int x = -fieldMap.length / 2; x < fieldMap.length / 2; x++) {
            int xIndex = x + fieldMap.length / 2;
            for (int y = -fieldMap[xIndex].length / 2; y < fieldMap[xIndex].length / 2; y++) {
                int yIndex = y + fieldMap[xIndex].length / 2;
                Point bottomRight = new Point(x + 1, y + 1);
                boolean isIncluded = fieldMap[xIndex][yIndex] != 0;
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
            // This will not work for non-square tiles.
            n.getValue().scale(24.0 / divisionsPerTile);

            System.out.println("Node: " + n);
        }


        return new FieldGraph(map);
    }
}
