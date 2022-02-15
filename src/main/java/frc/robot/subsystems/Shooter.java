package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    private final TalonFX mainMotor = new TalonFX(Constants.SHOOTER);
    private boolean active;

    public Shooter() {
        mainMotor.configFactoryDefault();

        mainMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 50);

        mainMotor.setSensorPhase(false);
        mainMotor.setInverted(false);

        register();
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

        SmartDashboard.putNumber("Temperature", mainMotor.getTemperature());
        SmartDashboard.putNumber("Current", mainMotor.getSupplyCurrent());
        SmartDashboard.putNumber("Voltage", mainMotor.getBusVoltage());
    }

    public void toggle() {
        active = !active;
    }
}
