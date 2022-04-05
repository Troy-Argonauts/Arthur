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

        shooterMain = new TalonFX(Constants.Shooter.PORT);

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

        shooterMain.config_kF(0, Constants.Shooter.kF);
        shooterMain.config_kP(0, Constants.Shooter.kP);
        shooterMain.config_kI(0, Constants.Shooter.kI);
        shooterMain.config_kD(0, Constants.Shooter.kD);
      
        shooterMain.configClosedloopRamp(Constants.Shooter.RAMP_SECONDS);
        shooterMain.configOpenloopRamp(Constants.Shooter.RAMP_SECONDS);
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
                active = true;
                break;
            case HIGH:
                shooterMain.set(ControlMode.PercentOutput, Constants.Shooter.HIGH_SPEED);
                active = true;
                break;
            case STOPPED:
                shooterMain.set(ControlMode.PercentOutput, 0);
                active = false;
                break;
        }
    }
}
