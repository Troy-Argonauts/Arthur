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

    private final TalonFX mainMotor;
    public static boolean active;

    public enum ClimberState {
        UP, DOWN, STOPPED
    }

    public Climber() {
        mainMotor = new TalonFX(Constants.Climber.PORT);

        mainMotor.setNeutralMode(NeutralMode.Brake);

        mainMotor.setInverted(true);

        mainMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        mainMotor.configFeedbackNotContinuous(false, 4);
        mainMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
    }

    public void periodic() {
        SmartDashboard.putBoolean("Climber Active", active);
    }

    public void setState(ClimberState state) {
        switch (state) {
            case UP:
                mainMotor.set(ControlMode.PercentOutput, Constants.Climber.UP_SPEED);
                active = true;
                break;
            case DOWN:
                mainMotor.set(ControlMode.PercentOutput, -Constants.Climber.DOWN_SPEED);
                active = true;
                break;
            case STOPPED:
                mainMotor.set(ControlMode.PercentOutput, 0);
                active = false;
                break;
        }
    }
}