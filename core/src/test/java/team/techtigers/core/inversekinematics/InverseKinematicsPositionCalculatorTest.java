package team.techtigers.core.inversekinematics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InverseKinematicsPositionCalculatorTest {

    @Test
    @DisplayName("Calculating two joints targets for reachable point returns correct servo values")
    void calculateTwoJointsForReachablePointReturnsCorrectValues() {
        assertArrayEquals(new double[]{Math.PI/2, 3*Math.PI/2},
                InverseKinematicsPositionCalculator.calculateTwoJoints(5, 5, 5, 5),
                0.001);
    }

    @Test
    @DisplayName("Calculating two joints targets for point directly above first joint returns correct servo values")
    void calculateTwoJointsForPointDirectlyAboveReturnsCorrectValues() {
        assertArrayEquals(new double[]{1.5707963267948966, 2*Math.PI},
                InverseKinematicsPositionCalculator.calculateTwoJoints(0, 10, 5, 5),
                0.001);
    }

    @Test
    @DisplayName("Calculating rotation for target point ahead returns correct orientation")
    void calculateRotationForTargetPointAheadReturnsCorrectOrientation() {
        assertEquals(0.0,
                InverseKinematicsPositionCalculator.calculateRotation(10, 0, 0),
                0.001);
    }

    @Test
    @DisplayName("Calculating rotation for target point behind adjusts orientation correctly")
    void calculateRotationForTargetPointBehindAdjustsOrientationCorrectly() {
        assertEquals(-Math.PI,
                InverseKinematicsPositionCalculator.calculateRotation(-10, 0, 0),
                0.001);
    }

    @Test
    @DisplayName("Calculating rotation adjusts for current heading")
    void calculateRotationAdjustsForCurrentHeading() {
        assertEquals(Math.PI,
                InverseKinematicsPositionCalculator.calculateRotation(0, -10, Math.PI / 2),
                0.001);
    }

    @Test
    @DisplayName("Calculating rotation normalizes angle to within -PI to PI range")
    void calculateRotationNormalizesAngle() {
        assertEquals(0,
                InverseKinematicsPositionCalculator.calculateRotation(0, -10, 3 * Math.PI / 2),
                0.001);
    }
}