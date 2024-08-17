package team.techtigers.core.paths.geometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PointTest {
    @Test
    @DisplayName("Test Polar Angle")
    void testPolarAngle() {
        Point p = new Point(3.0, 4.0);
        assertEquals(Math.atan(4.0 / 3.0), p.polarAngle());
    }

    @Test
    @DisplayName("Test Magnitude")
    void testMagnitude() {
        Point p = new Point(3.0, 4.0);
        assertEquals(5.0, p.magnitude());
    }

    @Test
    @DisplayName("Test Addition")
    void testAdd() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(3.0, 4.0);
        Point result = p1.add(p2);
        assertEquals(new Point(4.0, 6.0), result);
    }

    @Test
    @DisplayName("Test Subtraction")
    void testMinus() {
        Point p1 = new Point(3.0, 4.0);
        Point p2 = new Point(1.0, 2.0);
        Point result = p1.minus(p2);
        assertEquals(new Point(2.0, 2.0), result);
    }

    @Test
    @DisplayName("Test Scalar Multiplication")
    void testMultiply() {
        Point p = new Point(3.0, 4.0);
        Point result = p.multiply(2.0);
        assertEquals(new Point(6.0, 8.0), result);
    }

    @Test
    @DisplayName("Test Transformation")
    void testTransform() {
        Point p = new Point(1.0, 0.0);
        Point result = p.transform(Math.PI / 2);
        assertTrue(Math.abs(result.getX()) < 0.0001);
        assertTrue(Math.abs(result.getY() - 1) < 0.0001);
    }

    @Test
    @DisplayName("Test Distance")
    void testDist() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(4.0, 6.0);
        assertEquals(5.0, p1.dist(p2));
    }

    @Test
    @DisplayName("Test Angle")
    void testAngleTo() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(4.0, 6.0);
        assertEquals(Math.atan(4.0 / 3.0), p1.angleTo(p2));
    }

    @Test
    @DisplayName("Test Manhattan Distance")
    void testManhattan() {
        Point p1 = new Point(1.0, 2.0);
        Point p2 = new Point(4.0, 6.0);
        assertEquals(7.0, p1.manhattan(p2));
    }
}

