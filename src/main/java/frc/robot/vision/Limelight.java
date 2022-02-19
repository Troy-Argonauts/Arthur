package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {

    private NetworkTableInstance table = null;

    public enum LightMode {
        ON, OFF, BLINK
    }

    public enum CameraMode {
        VISION, DRIVER
    }

    private final static Limelight INSTANCE = new Limelight();

    private Limelight() {

    }

    public static Limelight getInstance() {
        return INSTANCE;
    }
    public boolean isOnTarget() {
        return getValue("tv").getDouble(0) == 1;
    }

    /**
     * Horizontal offset from crosshair to target (-27 degrees to 27 degrees).
     *
     * @return tx as reported by the Limelight.
     */
    public double getTx() {
        return getValue("tx").getDouble(0.00);
    }

    /**
     * Vertical offset from crosshair to target (-20.5 degrees to 20.5 degrees).
     *
     * @return ty as reported by the Limelight.
     */
    public double getTy() {
        return getValue("ty").getDouble(0.00);
    }

    /**
     * Area that the detected target takes up in total camera FOV (0% to 100%).
     *
     * @return Area of target.
     */
    public double getTa() {
        return getValue("ta").getDouble(0.00);
    }

    /**
     * Gets target skew or rotation (-90 degrees to 0 degrees).
     *
     * @return Target skew.
     */
    public double getTs() {
        return getValue("ts").getDouble(0.00);
    }

    /**
     * Gets target latency (ms).
     *
     * @return Target latency.
     */
    public double getTl() {
        return getValue("tl").getDouble(0.00);
    }

    /**
     * Sets LED mode of Limelight.
     *
     * @param mode
     *            Light mode for Limelight.
     */
    public void setLedMode(LightMode mode) {
        getValue("ledMode").setNumber(mode.ordinal());
    }

    /**
     * Sets camera mode for Limelight.
     *
     * @param mode
     *            Camera mode for Limelight.
     */
    public void setCameraMode(CameraMode mode) {
        getValue("camMode").setNumber(mode.ordinal());
    }

    /**
     * Sets pipeline number (0-9 value).
     *
     * @param number
     *            Pipeline number (0-9).
     */
    public void setPipeline(int number) {
        getValue("pipeline").setNumber(number);
    }

    public double getDistanceFromTargetInches()  {
        double angle = Constants.LIMELIGHT_ANGLE + getTy();
        if (angle < 1 || angle > 89)
            return 0;
        double tanTerm = Math.tan(Math.toRadians(angle));
        return (Constants.VERTICAL_HUB_HEIGHT - Constants.LIMELIGHT_HEIGHT) / tanTerm;
    }

    /**
     * Helper method to get an entry from the Limelight NetworkTable.
     *
     * @param key
     *            Key for entry.
     * @return NetworkTableEntry of given entry.
     */
    private NetworkTableEntry getValue(String key) {
        if (table == null) {
            table = NetworkTableInstance.getDefault();
        }

        return table.getTable("limelight").getEntry(key);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Limelight Distance", getDistanceFromTargetInches());
    }
    
}