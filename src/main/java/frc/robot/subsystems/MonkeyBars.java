package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MonkeyBars extends SubsystemBase {

    private final TalonFX mainMotor;

    public MonkeyBars() {
        mainMotor = new TalonFX(Constants.MONKEY_BARS);

        mainMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void up() {
        mainMotor.set(ControlMode.PercentOutput, 0.3);
    }

    public void down() {
        mainMotor.set(ControlMode.PercentOutput, -0.3);
    }

    public void stop() {
        mainMotor.set(ControlMode.PercentOutput, 0);
    }
}
