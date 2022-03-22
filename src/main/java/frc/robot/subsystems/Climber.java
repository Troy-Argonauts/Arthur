package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

    private final TalonFX rightMotor, leftMotor;
    public static boolean active, extended;

    public Climber() {
        rightMotor = new TalonFX(Constants.MonkeyBars.CLIMBER_RIGHT);
        rightMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.setInverted(true);
        rightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        rightMotor.configFeedbackNotContinuous(false, 4);
        rightMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);

        leftMotor = new TalonFX(Constants.MonkeyBars.CLIMBER_LEFT);
        leftMotor.setNeutralMode(NeutralMode.Brake);
        leftMotor.setInverted(false);
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        leftMotor.configFeedbackNotContinuous(false, 4);
        leftMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
    }

    public void periodic() {
        SmartDashboard.putBoolean("Climber Active", active);
        SmartDashboard.putBoolean("Climber Extended", extended);
    }

    public void up() {
        rightMotor.set(ControlMode.PercentOutput, 0.5);
        leftMotor.set(ControlMode.PercentOutput, 0.5);
        active = true;
    }

    public void down() {
        rightMotor.set(ControlMode.PercentOutput, -0.35);
        leftMotor.set(ControlMode.PercentOutput, -0.35);
        active = true;
    }

    public void stop() {
        rightMotor.set(ControlMode.PercentOutput, 0);
        leftMotor.set(ControlMode.PercentOutput, 0);
        active = false;
    }

    public void extended() {
        extended = true;
    }

    public void retracted() {
        extended = false;
    }
}