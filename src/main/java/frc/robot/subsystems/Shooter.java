package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

        shooterMain = new TalonFX(Constants.SHOOTER);

        shooterMain.configFactoryDefault();

        shooterMain.setSensorPhase(false);
        shooterMain.setInverted(false);

        StatorCurrentLimitConfiguration statorCurrentLimitConfiguration = new StatorCurrentLimitConfiguration();
        statorCurrentLimitConfiguration.currentLimit = 60;
        statorCurrentLimitConfiguration.enable = true;
        statorCurrentLimitConfiguration.triggerThresholdCurrent = 80;
        statorCurrentLimitConfiguration.triggerThresholdTime = 0.5;

        shooterMain.configStatorCurrentLimit(statorCurrentLimitConfiguration);

        shooterMain.config_kF(0, Constants.SHOOTER_F);
        shooterMain.config_kP(0, Constants.SHOOTER_P);
        shooterMain.config_kI(0, Constants.SHOOTER_I);
        shooterMain.config_kD(0, Constants.SHOOTER_D);

    }

    @Override
    public void periodic() {
        if (active) {
            activate();
        } else {
            stop();
        }

        SmartDashboard.putNumber("Shooter Temperature", shooterMain.getTemperature());
        SmartDashboard.putNumber("Shooter Current", shooterMain.getSupplyCurrent());
        SmartDashboard.putNumber("Shooter Voltage", shooterMain.getBusVoltage());
        SmartDashboard.putNumber("Shooter RPM", getRPM());
        SmartDashboard.putNumber("Shooter Stator Current", shooterMain.getStatorCurrent());
        SmartDashboard.putNumber("Shooter Supply Current", shooterMain.getSupplyCurrent());
    }

    public double rpmToNativeUnits(double rpm) {
        return rpm * Constants.ENCODER_TICKS_PER_MOTOR_REVOLUTION / 10.0 / 60.0;
    }

    public double getRPM() {
        return shooterMain.getSelectedSensorPosition() / Constants.ENCODER_TICKS_PER_MOTOR_REVOLUTION;
    }

    public boolean lockOn() {
        // TODO: Make lock on method so shooter speed can be made so that it can shoot from anywhere on the field
        return false;
    }

    public void activate() {
        shooterMain.set(ControlMode.PercentOutput, 0.55);
    }

    public void stop() {
        shooterMain.set(ControlMode.PercentOutput, 0);
    }

    public void toggle() {
        active = !active;
    }
}
