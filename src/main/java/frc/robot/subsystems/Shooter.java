package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    private final TalonFX shooterMain;
    private boolean active;

    public Shooter() {
        active = false;

        shooterMain = new TalonFX(Constants.Shooter.SHOOTER);

        shooterMain.configFactoryDefault();

        shooterMain.setNeutralMode(NeutralMode.Coast);

        shooterMain.setSensorPhase(false);
        shooterMain.setInverted(false);

        StatorCurrentLimitConfiguration statorCurrentLimitConfiguration = new StatorCurrentLimitConfiguration();
        statorCurrentLimitConfiguration.currentLimit = 60;
        statorCurrentLimitConfiguration.enable = true;
        statorCurrentLimitConfiguration.triggerThresholdCurrent = 80;
        statorCurrentLimitConfiguration.triggerThresholdTime = 0.5;

        shooterMain.configStatorCurrentLimit(statorCurrentLimitConfiguration);

        shooterMain.config_kF(0, Constants.Shooter.SHOOTER_F);
        shooterMain.config_kP(0, Constants.Shooter.SHOOTER_P);
        shooterMain.config_kI(0, Constants.Shooter.SHOOTER_I);
        shooterMain.config_kD(0, Constants.Shooter.SHOOTER_D);
      
        shooterMain.configClosedloopRamp(Constants.Shooter.SHOOTER_NEUTRALTORAMPSECONDS);
        shooterMain.configOpenloopRamp(Constants.Shooter.SHOOTER_NEUTRALTORAMPSECONDS);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Shooter RPM", getRPM());
        SmartDashboard.putNumber("Shooter Percentage", shooterMain.getMotorOutputPercent());
        SmartDashboard.putBoolean("Shooter Activated", active);
    }

    public double rpmToNativeUnits(double rpm) {
        return rpm * Constants.Shooter.ENCODER_TICKS_PER_MOTOR_REVOLUTION / 10.0 / 60.0;
    }

    public double getRPM() {
        return shooterMain.getSelectedSensorPosition() / Constants.Shooter.ENCODER_TICKS_PER_MOTOR_REVOLUTION;
    }

    public boolean lockOn() {
        // TODO: Make lock on method so shooter speed can be made so that it can shoot from anywhere on the field
        return false;
    }

    public void lowGoal() {
        shooterMain.set(ControlMode.PercentOutput, 0.50);
        active = true;
    }

    public void highGoal() {
        shooterMain.set(ControlMode.PercentOutput, 0.95);
        active = true;
    }

    public void stop() {
        shooterMain.set(ControlMode.PercentOutput, 0);
        active = false;
    }
}
