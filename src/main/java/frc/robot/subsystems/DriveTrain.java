package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DT_CheesyDrive;

public class DriveTrain extends SubsystemBase {

    /**
     * Creates all 4 motors of the robot and sets them to IDs defined in the Constants class
      */
    private TalonFX frontLeft = new TalonFX(Constants.DT_FRONT_LEFT);
    private TalonFX frontRight = new TalonFX(Constants.DT_FRONT_RIGHT);
    private TalonFX rearLeft = new TalonFX(Constants.DT_REAR_LEFT);
    private TalonFX rearRight = new TalonFX(Constants.DT_REAR_RIGHT);

    /**
     * Has the rear motors imitate the front motors to reduce lines of code
     */
    public DriveTrain() {
        rearLeft.follow(frontLeft);
        rearRight.follow(frontRight);

        setDefaultCommand(new DT_CheesyDrive());
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
        frontLeft.set(ControlMode.PercentOutput, (speed-turn) / 2);
        frontRight.set(ControlMode.PercentOutput, (speed+turn) / 2);
    }

    public void breakMode() {
        frontLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {

    }


    @Override
    public void simulationPeriodic() {

    }
}