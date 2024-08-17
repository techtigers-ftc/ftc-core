package team.techtigers.core.paths.routeplanning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class DijkstraTest {
    @Test
    @DisplayName("Test Dijkstra")
    void test() {
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        n1.addConnections(Arrays.asList(new Connection(n2, 10), new Connection(n3, 5)));
        n2.addConnection(new Connection(n4, 5));
        n3.addConnection(new Connection(n4, 7));
        Dijkstra dj = new Dijkstra();
        ArrayList<Node> path = dj.findPath(n1, n4);
        Node[] expected = new Node[] {n1, n3, n4};

        assertEquals(path.size(), expected.length);
        for (int i = 0; i < path.size(); i++) {
            assertEquals(path.get(i), expected[i]);
        }
    }
}
