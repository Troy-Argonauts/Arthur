package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.HashMap;

public class Shooter extends SubsystemBase {

    private final TalonFX shooterMain;
    private final TalonFX shooterSlave;
    private boolean active;
    public static double MAIN_LOW_SPEED, SLAVE_LOW_SPEED;
    public static int PRESET_POSITION;

    public Shooter() {
        active = false;
        MAIN_LOW_SPEED = Constants.Shooter.MAIN_LOW_SPEED;
        SLAVE_LOW_SPEED = Constants.Shooter.SLAVE_LOW_SPEED;

        shooterMain = new TalonFX(Constants.Shooter.PORT);
        shooterSlave = new TalonFX(Constants.Shooter.SLAVE_PORT);

        shooterMain.configFactoryDefault();
        shooterSlave.configFactoryDefault();

        shooterMain.setNeutralMode(NeutralMode.Coast);
        shooterSlave.setNeutralMode(NeutralMode.Coast);

        shooterMain.setSensorPhase(false);
        shooterMain.setInverted(true);
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
        SmartDashboard.putString("Main High Speed", Shooter.MAIN_LOW_SPEED * 100 + "%");
        SmartDashboard.putString("Slave High Speed", Shooter.SLAVE_LOW_SPEED * 100 + "%");
    }

    public enum ShooterState {
        LOW, HIGH, STOPPED
    }

    public void setState(ShooterState state) {
        switch (state) {
            case LOW:
                shooterMain.set(ControlMode.PercentOutput, MAIN_LOW_SPEED);
                shooterSlave.set(ControlMode.PercentOutput, SLAVE_LOW_SPEED);
                active = true;
                break;
            case HIGH:
                shooterMain.set(ControlMode.PercentOutput, Constants.Shooter.MAIN_HIGH_SPEED);
                shooterSlave.set(ControlMode.PercentOutput, Constants.Shooter.SLAVE_HIGH_SPEED);
                active = true;
                break;
            case STOPPED:
                shooterMain.set(ControlMode.PercentOutput, 0);
                shooterSlave.set(ControlMode.PercentOutput, 0);
                active = false;
                break;
        }
    }

    public void setPreset() {
        if (PRESET_POSITION > 3) {
            PRESET_POSITION = 0;
        } else if (PRESET_POSITION < 0) {
            PRESET_POSITION = 3;
        }

        String[] presetArray = {"Fender Low", "Fender High", "Tarmac High", "LaunchPad High"};
        double[] presetMainSpeeds = {0.1, 0.33, 0.4, 0.67};
        double[] presetSlaveSpeeds = {0.1, 0.33, 0.4, 0.67};

        SmartDashboard.putString("Preset", presetArray[PRESET_POSITION]);
        MAIN_LOW_SPEED = presetMainSpeeds[PRESET_POSITION];
        SLAVE_LOW_SPEED = presetSlaveSpeeds[PRESET_POSITION];
    }
}
