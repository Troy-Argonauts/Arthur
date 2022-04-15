package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    private final TalonFX shooterFront;
    private final TalonFX shooterBack;
    private boolean active;
    public static double FRONT_SPEED, BACK_SPEED;
    public static int PRESET_POSITION;

    public Shooter() {
        active = false;

        FRONT_SPEED = Constants.Shooter.FRONT_DEFAULT_SPEED;
        BACK_SPEED = Constants.Shooter.BACK_DEFAULT_SPEED;

        shooterFront = new TalonFX(Constants.Shooter.PORT);
        shooterBack = new TalonFX(Constants.Shooter.SLAVE_PORT);

        shooterFront.configFactoryDefault();
        shooterBack.configFactoryDefault();

        shooterFront.setNeutralMode(NeutralMode.Coast);
        shooterBack.setNeutralMode(NeutralMode.Coast);

        shooterFront.setSensorPhase(false);
        shooterFront.setInverted(true);
        shooterBack.setSensorPhase(false);
        shooterBack.setInverted(false);

        StatorCurrentLimitConfiguration statorCurrentLimitConfiguration = new StatorCurrentLimitConfiguration();
        statorCurrentLimitConfiguration.currentLimit = 60;
        statorCurrentLimitConfiguration.enable = true;
        statorCurrentLimitConfiguration.triggerThresholdCurrent = 80;
        statorCurrentLimitConfiguration.triggerThresholdTime = 0.5;

        shooterFront.configStatorCurrentLimit(statorCurrentLimitConfiguration);
        shooterBack.configStatorCurrentLimit(statorCurrentLimitConfiguration);

        shooterFront.config_kF(0, Constants.Shooter.F);
        shooterFront.config_kP(0, Constants.Shooter.P);
        shooterFront.config_kI(0, Constants.Shooter.I);
        shooterFront.config_kD(0, Constants.Shooter.D);
        shooterBack.config_kF(0, Constants.Shooter.F);
        shooterBack.config_kP(0, Constants.Shooter.P);
        shooterBack.config_kI(0, Constants.Shooter.I);
        shooterBack.config_kD(0, Constants.Shooter.D);

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
        SmartDashboard.putString("Main High Speed", (int) (Shooter.FRONT_SPEED * 100) + "%");
        SmartDashboard.putString("Slave High Speed", (int) (Shooter.BACK_SPEED * 100) + "%");
    }

    public enum ShooterState {
        SHOOT, STOPPED
    }

    public void setState(ShooterState state) {
        switch (state) {
            case SHOOT:
                shooterFront.set(ControlMode.PercentOutput, FRONT_SPEED);
                shooterBack.set(ControlMode.PercentOutput, BACK_SPEED);
                active = true;
                break;
            case STOPPED:
                shooterFront.set(ControlMode.PercentOutput, 0);
                shooterBack.set(ControlMode.PercentOutput, 0);
                active = false;
                break;
        }
    }

    public void setPreset() {
        String[] presetArray = {"Fender Low", "Fender High", "Tarmac High"};
        double[] presetFrontSpeeds = {0.2, 0.33, 0.4};
        double[] presetBackSpeeds = {0.1, 0.33, 0.4};

        if (PRESET_POSITION > (presetArray.length - 1)) {
            PRESET_POSITION = 0;
        } else if (PRESET_POSITION < 0) {
            PRESET_POSITION = (presetArray.length - 1);
        }

        SmartDashboard.putString("Preset", presetArray[PRESET_POSITION]);
        FRONT_SPEED = presetFrontSpeeds[PRESET_POSITION];
        BACK_SPEED = presetBackSpeeds[PRESET_POSITION];
    }
}
