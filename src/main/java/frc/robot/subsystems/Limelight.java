package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {
    
    private static NetworkTable getTable() {
        return NetworkTableInstance.getDefault().getTable("limelight");
    }

    private final NetworkTableEntry xAngle = getTable().getEntry("tx");
    private final NetworkTableEntry yAngle = getTable().getEntry("ty");

    public double getXAngle() {
        return xAngle.getDouble(0);
    }

    public double getYAngle() {
        return yAngle.getDouble(0);
    }

    public double distance() {
        return Constants.LL_SHOT_HEIGHT / Math.abs(Math.toRadians(getYAngle()));
    }

}
