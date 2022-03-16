package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MonkeyBars extends SubsystemBase {

    private final TalonFX mainMotor;
    public static boolean active;

    public MonkeyBars() {
        mainMotor = new TalonFX(Constants.MONKEY_BARS);

        mainMotor.setNeutralMode(NeutralMode.Brake);

        mainMotor.setInverted(true);

        mainMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        mainMotor.configFeedbackNotContinuous(false, 4);
        mainMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
    }

    public void periodic() {
        SmartDashboard.putNumber("Climber Encoder", getEncoderValue());
        SmartDashboard.putBoolean("Climber Active", active);
    }

    public void up() {
        mainMotor.set(ControlMode.PercentOutput, 0.5);
        active = true;
    }
    public void down() {
        mainMotor.set(ControlMode.PercentOutput, -0.35);
        active = true;
    }
    public void stop() {
        mainMotor.set(ControlMode.PercentOutput, 0);
        active = false;
    }

    public double getEncoderValue() {
        return mainMotor.getSelectedSensorPosition();
    }
}