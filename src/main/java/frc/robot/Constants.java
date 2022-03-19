// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public interface DriveTrain {
        // DriveTrain Ports
        int DT_FRONT_RIGHT = 3;
        int DT_FRONT_LEFT = 1;
        int DT_REAR_RIGHT = 4;
        int DT_REAR_LEFT = 2;

        double DT_kWheelDiameterMeters = 0.2;
        double DT_kEncoderCPR = 2048;
        double DT_kEncoderDistancePerPulse = (DT_kWheelDiameterMeters * Math.PI)/ DT_kEncoderCPR;
        double DT_kP = 0.02;
        double DT_kI = 0;
        double DT_kD = 0;
        double DT_kS = 0.20094;
        double DT_kV = 1.6658;
        double DT_kA = 0.4515;
        double DT_PIDTolerance = 0.5;
        double ROBOT_WIDTH = Units.inchesToMeters(33); // Meters
        double DT_NEUTRALTORAMPSECONDS = 0.75;
    }

    public interface Intake {
        int I_INTAKE = 5;
    }

    public interface Indexer {
        int I_INDEXER_FLOOR = 6;
        int I_INDEXER_UP = 7;
    }

    public interface Pneumatics {
        int INTAKE_SOLENOID_1 = 0;
        int INTAKE_SOLENOID_2 = 1;
    }

    public interface Shooter {
        int SHOOTER = 9;
        double SHOOTER_F = 0.05;
        double SHOOTER_P = 0;
        double SHOOTER_I = 0;
        double SHOOTER_D = 0;
        double ENCODER_TICKS_PER_MOTOR_REVOLUTION = 2048.0;
        double SHOOTER_NEUTRALTORAMPSECONDS = 1.75;
    }

    public interface Limelight {
        double LIMELIGHT_HEIGHT = 0;
        double VERTICAL_HUB_HEIGHT = 0;
        double LIMELIGHT_ANGLE = 0;
    }

    public interface MonkeyBars {
        int MONKEY_BARS = 8;
    }

    public interface Controller {
        int DRIVER_PORT = 0;
        int OPERATOR_PORT = 1;
        double CONTROLLER_DRIFT = 0.07;
    }
}
