package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    private TalonFX frontLeft = new TalonFX(Constants.DT_FRONT_LEFT);
    private TalonFX frontRight = new TalonFX(Constants.DT_FRONT_RIGHT);
    private TalonFX rearLeft = new TalonFX(Constants.DT_REAR_LEFT);
    private TalonFX rearRight = new TalonFX(Constants.DT_REAR_RIGHT);

    public DriveTrain() {
        rearLeft.follow(frontLeft);
        rearRight.follow(frontRight);
    }

    public void tankDrive(double left, double right) {
        frontLeft.set(ControlMode.PercentOutput, left);
        frontRight.set(ControlMode.PercentOutput, left);
    }

    @Override
    public void periodic() {

    }


    @Override
    public void simulationPeriodic() {

    }
}