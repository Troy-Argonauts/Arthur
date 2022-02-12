package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    private final TalonFX mainMotor = new TalonFX(Constants.SHOOTER);
    private boolean active;

    public Shooter() {
        mainMotor.configFactoryDefault();
        active = false;
    }

    @Override
    public void periodic() {
        if (!active) {
            mainMotor.set(ControlMode.PercentOutput, 0.55);
            active = true;
        } else {
            mainMotor.set(ControlMode.PercentOutput, 0);
            active = false;
        }
    }

    public void toggle() {
        active = !active;
    }
}
