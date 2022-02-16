package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    /**
     * Creates all 2 main motors of the robot
     */

    private final TalonFX frontLeft;
    private final TalonFX frontRight;

    /**
     * Sets the values of the frontLeft and frontRight motors, and creates local rear motors.
     * Has rear motors follow front motors, and sets all motors to coast when stopped.
     */
    public DriveTrain() {
        frontLeft = new TalonFX(Constants.DT_FRONT_LEFT);
        frontRight = new TalonFX(Constants.DT_FRONT_RIGHT);
        TalonFX rearLeft = new TalonFX(Constants.DT_REAR_LEFT);
        TalonFX rearRight = new TalonFX(Constants.DT_REAR_RIGHT);


        rearLeft.follow(frontLeft);
        rearRight.follow(frontRight);

        frontLeft.setInverted(true);
        rearLeft.setInverted(InvertType.FollowMaster);

        frontLeft.setNeutralMode(NeutralMode.Coast);
        frontRight.setNeutralMode(NeutralMode.Coast);
        rearLeft.setNeutralMode(NeutralMode.Coast);
        rearRight.setNeutralMode(NeutralMode.Coast);
    }

    /**
     * Has the robot move at a certain speed output, usually using input from Joysticks
     * @param left Sets the left side motors to a certain percent output
     * @param right Sets the right side motors to a certain percent output
     */
    public void tankDrive(double left, double right) {
        frontLeft.set(ControlMode.PercentOutput, left);
        frontRight.set(ControlMode.PercentOutput, right);
    }

    /**
     * Has the robot move at a certain speed, but allows the robot to turn, using input from Joysticks
     * @param turn Amount to turn the robot
     * @param speed Speed of robot
     */
    public void cheesyDrive(double turn, double speed) {
        frontLeft.set(ControlMode.PercentOutput, (speed - turn) / 2);
        frontRight.set(ControlMode.PercentOutput, (speed + turn) / 2);
    }

    @Override
    public void periodic() {

    }


    @Override
    public void simulationPeriodic() {

    }
}