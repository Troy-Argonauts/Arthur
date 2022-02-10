package frc.robot.subsystems;

import javax.swing.UIDefaults.ActiveValue;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    private final TalonFX mainMotor = new TalonFX(Constants.SHOOTER);

    public Shooter() {
        mainMotor.configFactoryDefault();
    }

    public void activate() {
        mainMotor.set(ControlMode.PercentOutput, 0.35/*0.55*/);
    }

    public void deactivate() {
        mainMotor.set(ControlMode.PercentOutput, 0);
    }
    public boolean toggle(boolean active) {
        if (!active) {
            activate();
            active = true;
        } else {
            deactivate();
            active = false;
        }
        return active;
    }
}
