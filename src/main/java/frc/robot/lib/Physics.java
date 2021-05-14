package frc.robot.lib;

public class Physics {

    public Physics() {
    }
    
    /**
     * Calculate speed based on distance traveled
     * 
     * @param distanceInMeters
     * @param timeInSeconds
     * @return speed meters/second
     */
    public static double speed(double distanceInMeters, double timeInSeconds) {
        return distanceInMeters / timeInSeconds;
    }

    /**
     * Calculate speed based on initial and stop positions
     * 
     * @param initialPosition
     * @param endPosition
     * @param timeInSeconds
     * @return speed
     */
    public static double speed(double initialPosition, double endPosition, double timeInSeconds) {
        return (endPosition - initialPosition) / timeInSeconds;
    }

    /**
     * Calculate speed based on initial and stop position and initial and stop time.
     * 
     * @param initialPosition
     * @param endPosition
     * @param initialTime
     * @param endTime
     * @return
     */
    public static double speed(double initialPosition, double endPosition, double initialTime, double endTime) {
        double distanceDelta = endPosition - initialPosition;
        double timeDelta = endTime - initialTime;

        return distanceDelta / timeDelta;
    }

    /**
     * Calculate velocity based on distance traveled
     * 
     * @param distanceInMeters
     * @param timeInSeconds
     * @return velocity meters/second
     */
    public static double velocity(double distanceInMeters, double timeInSeconds) {
        return distanceInMeters / timeInSeconds;
    }

    /**
     * Calculate velocity based on initial and stop positions
     * 
     * @param initialPosition
     * @param endPosition
     * @param timeInSeconds
     * @return velocity
     */
    public static double velocity(double initialPosition, double endPosition, double timeInSeconds) {
        return (endPosition - initialPosition) / timeInSeconds;
    }

    /**
     * Calculate velocity based on initial and stop position and initial and stop time.
     * 
     * @param initialPosition
     * @param endPosition
     * @param initialTime
     * @param endTime
     * @return
     */
    public static double velocity(double initialPosition, double endPosition, double initialTime, double endTime) {
        double distanceDelta = endPosition - initialPosition;
        double timeDelta = endTime - initialTime;

        return distanceDelta / timeDelta;
    }

    /**
     * Calculate acceleration based on velocity delta and time
     * 
     * @param velocityDelta
     * @param timeInSeconds
     * @return
     */
    public static double acceleration(double velocityDelta, double timeInSeconds) {
        return velocityDelta / timeInSeconds;
    }

    /**
     * Calculate acceleration based on initial and end velocity and time
     * @param initialVelocity
     * @param endVelocity
     * @param timeInSeconds
     * @return
     */
    public static double acceleration(double initialVelocity, double endVelocity, double timeInSeconds) {
        return (endVelocity - initialVelocity) / timeInSeconds;
    }

    /**
     * Calculated acceleration based on initial and end velocity and initial and end time
     * @param initialVelocity
     * @param endVelocity
     * @param initialTime
     * @param endTime
     * @return
     */
    public static double acceleration(double initialVelocity, double endVelocity, double initialTime, double endTime) {
        double velocityDelta = endVelocity - initialVelocity;
        double timeDelta = endTime - initialTime;

        return velocityDelta / timeDelta;
    }

    /**
     * Calculate angular velocity based on motor's rotations per minute
     * 
     * @param rpm
     * @return angularVelocity meters per second
     */
    public static double angularVelocity(double rpm) {
        return ((2 * Math.PI) * rpm) / 60;
    }

    /**
     * Calculate linear velocity based on angular velocity and wheel radius
     * 
     * @param angularVelocity
     * @param wheelRadius
     * @return
     */
    public static double linearVelocity(double angularVelocity, double wheelRadius) {
        return angularVelocity * wheelRadius;
    }

    /**
     * Calculate angular acceleration based on angular velocity delta and time delta.
     * 
     * @param angularVelocityDelta
     * @param timeDelta
     * @return
     */
    public static double angularAcceleration(double angularVelocityDelta, double timeDelta) {
        return angularVelocityDelta / timeDelta;
    }

    /**
     * Calculate angular acceleration based on initial and end angular velocity and time delta.
     * 
     * @param initialAngularVelocity
     * @param endAngularVelocity
     * @param timeDelta
     * @return
     */
    public static double angularAcceleration(double initialAngularVelocity, double endAngularVelocity, double timeDelta) {
        return (endAngularVelocity - initialAngularVelocity) / timeDelta;
    }

    /**
     * Calculate angular acceleration based on initial and end angular velocity and initial and end time.
     * 
     * @param initialAngularVelocity
     * @param endAngularVelocity
     * @param initialTime
     * @param endTime
     * @return
     */
    public static double angularAcceleration(double initialAngularVelocity, double endAngularVelocity, double initialTime, double endTime) {
        double angularVelocityDelta = endAngularVelocity - initialAngularVelocity;
        double timeDelta = endTime - initialTime;

        return angularVelocityDelta / timeDelta;
    }

    /**
     * Calculate linear acceleration based on 
     * 
     * @param angularAcceleration
     * @param wheelRadius
     * @return
     */
    public static double linearAcceleration(double angularAcceleration, double wheelRadius) {
        return angularAcceleration * wheelRadius;
    }
}
