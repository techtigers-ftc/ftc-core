package team.techtigers.core.inversekinematics;

/**
 * Utility class used to calculate base inverse kinematics actions
 */
public class InverseKinematicsPositionCalculator {

    /**
     * Will calculate servo values for a 2 joint movement to a specific point (in inches)
     *
     * @param x  The distance to the target point on the field plane
     * @param height  The height of the target point
     * @param l1 The length of the first joint
     * @param l2 The length of the second joint
     * @return An array with 2 target servo values, in the order of the joint closest to the robot first
     */
    public static double[] calculateTwoJoints(double x, double height, double l1, double l2) {

        double q2num = Math.pow(x, 2) + Math.pow(height, 2) - Math.pow(l1, 2) - Math.pow(l2, 2);
        double q2den = 2 * l1 * l2;
        double ratio = Math.min(q2num / q2den, 1);
        double q2 = Math.PI + Math.acos(-ratio);

        double q1 = Math.atan2(height, x) - Math.atan2(l2 * Math.sin(q2), (l1 + l2 * Math.cos(q2)));

        return new double[]{q1, q2};
    }

    /**
     * Will calculate servo values for a rotation to a specific point while
     * accounting for the current heading of the robot
     *
     * @param x              The delta x value of the target point (Field plane)
     * @param y              The delta y value of the target point (Field plane)
     * @param currentHeading The current heading of the robot
     * @return An angle for the system to orient to (in radians)
     */
    public static double calculateRotation(double x, double y, double currentHeading) {
        double totalAngle = -Math.atan2(y, x) + currentHeading;
        if (totalAngle > Math.PI) {
            totalAngle -= 2*Math.PI;
        }
        return totalAngle;
    }
}