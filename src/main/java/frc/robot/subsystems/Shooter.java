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

    public void activate() {
        mainMotor.set(ControlMode.PercentOutput, 0.55);
    }

    public void deactivate() {
        mainMotor.set(ControlMode.PercentOutput, 0);
    }

    public void toggle() {
        if (!active) {
            activate();
            active = true;
        } else {
            deactivate();
            active = false;
        }
    }

    public boolean isActive() {
        return active;
    }
}
