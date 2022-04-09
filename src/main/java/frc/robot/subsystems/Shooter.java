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
    private final TalonFX shooterSlave;
    private boolean active;

    public Shooter() {
        active = false;

        shooterMain = new TalonFX(Constants.Shooter.PORT);
        shooterSlave = new TalonFX(Constants.Shooter.SLAVE_PORT);

        shooterMain.configFactoryDefault();
        shooterSlave.configFactoryDefault();

        shooterMain.setNeutralMode(NeutralMode.Coast);
        shooterSlave.setNeutralMode(NeutralMode.Coast);

        shooterMain.setSensorPhase(false);
        shooterMain.setInverted(false);
        shooterSlave.setSensorPhase(false);
        shooterSlave.setInverted(false);

        StatorCurrentLimitConfiguration statorCurrentLimitConfiguration = new StatorCurrentLimitConfiguration();
        statorCurrentLimitConfiguration.currentLimit = 60;
        statorCurrentLimitConfiguration.enable = true;
        statorCurrentLimitConfiguration.triggerThresholdCurrent = 80;
        statorCurrentLimitConfiguration.triggerThresholdTime = 0.5;

        shooterMain.configStatorCurrentLimit(statorCurrentLimitConfiguration);
        shooterSlave.configStatorCurrentLimit(statorCurrentLimitConfiguration);

        shooterMain.config_kF(0, Constants.Shooter.F);
        shooterMain.config_kP(0, Constants.Shooter.P);
        shooterMain.config_kI(0, Constants.Shooter.I);
        shooterMain.config_kD(0, Constants.Shooter.D);
        shooterSlave.config_kF(0, Constants.Shooter.F);
        shooterSlave.config_kP(0, Constants.Shooter.P);
        shooterSlave.config_kI(0, Constants.Shooter.I);
        shooterSlave.config_kD(0, Constants.Shooter.D);
      
        shooterMain.configClosedloopRamp(Constants.Shooter.RAMP_SECONDS);
        shooterMain.configOpenloopRamp(Constants.Shooter.RAMP_SECONDS);
        shooterSlave.configClosedloopRamp(Constants.Shooter.RAMP_SECONDS);
        shooterSlave.configOpenloopRamp(Constants.Shooter.RAMP_SECONDS);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Shooter Activated", active);
    }

    public enum ShooterState {
        LOW, HIGH, STOPPED
    }

    public void setState(ShooterState state) {
        switch (state) {
            case LOW:
                shooterMain.set(ControlMode.PercentOutput, Constants.Shooter.LOW_SPEED);
                shooterSlave.set(ControlMode.PercentOutput, Constants.Shooter.LOW_SPEED);
                active = true;
                break;
            case HIGH:
                shooterMain.set(ControlMode.PercentOutput, Constants.Shooter.HIGH_SPEED);
                shooterSlave.set(ControlMode.PercentOutput, Constants.Shooter.HIGH_SPEED);
                active = true;
                break;
            case STOPPED:
                shooterMain.set(ControlMode.PercentOutput, 0);
                shooterSlave.set(ControlMode.PercentOutput, 0);
                active = false;
                break;
        }
    }
}
