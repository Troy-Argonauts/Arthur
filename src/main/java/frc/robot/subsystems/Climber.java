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

    public enum ClimberState {
        EXTEND,
        RETRACT,
        STOPPED
    }

    public Climber() {
        rightMotor = new TalonFX(Constants.Climber.RIGHT_PORT);
        rightMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.setInverted(false);
        rightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        rightMotor.configFeedbackNotContinuous(false, 4);
        rightMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);

        leftMotor = new TalonFX(Constants.Climber.LEFT_PORT);
        leftMotor.setNeutralMode(NeutralMode.Brake);
        leftMotor.setInverted(true);
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        leftMotor.configFeedbackNotContinuous(false, 4);
        leftMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
    }

    public void setState(ClimberState state) {
        switch (state) {
            case EXTEND:
                rightMotor.set(ControlMode.PercentOutput, Constants.Climber.CLIMBER_SPEED);
                leftMotor.set(ControlMode.PercentOutput, Constants.Climber.CLIMBER_SPEED);
                break;
            case RETRACT:
                rightMotor.set(ControlMode.PercentOutput, -Constants.Climber.CLIMBER_SPEED);
                leftMotor.set(ControlMode.PercentOutput, -Constants.Climber.CLIMBER_SPEED);
                break;
            case STOPPED:
                rightMotor.set(ControlMode.PercentOutput, 0);
                leftMotor.set(ControlMode.PercentOutput, 0);
                break;
        }
    }
}