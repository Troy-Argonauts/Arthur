package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;

public class LimeLight {

    private static NetworkTable getTable() {
        return NetworkTableInstance.getDefault().getTable("limelight");
    }

    public static void start() {
        getTable().getEntry("camMode").setNumber(0);
    }

    public static double getXAngle() {
        return getTable().getEntry("tx").getDouble(0.0);
    }

    public static double getYAngle() {
        return getTable().getEntry("ty").getDouble(0.0);
    }

    public static boolean contourFound() {
        return getTable().getEntry("tv").getDouble(0.0) == 1;
    }

    public static double getContourArea() {
        return getTable().getEntry("ta").getDouble(0.0);
    }

    public double distance() {
        return Constants.LL_SHOOTERTOHUB_Y / Math.sin(getYAngle());
    }
    
}