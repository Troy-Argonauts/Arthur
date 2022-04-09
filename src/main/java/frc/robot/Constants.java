// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {

    public interface DriveTrain {
        // DriveTrain Ports
        int FRONT_RIGHT = 3;
        int FRONT_LEFT = 1;
        int REAR_RIGHT = 4;
        int REAR_LEFT = 2;

        double WHEEL_DIAMETER_METERS = 0.2;
        double ENCODER_CPR = 2048;
        double ENCODER_DISTANCE_PER_PULSE = (WHEEL_DIAMETER_METERS * Math.PI)/ ENCODER_CPR;
        double P = 0.02;
        double I = 0;
        double D = 0;
        double S = 0.20094;
        double V = 1.6658;
        double A = 0.4515;
        double PIDTolerance = 0.5;
        double ROBOT_WIDTH = Units.inchesToMeters(33); // Meters
        double RAMP_SECONDS = 0.75;
    }

    public interface Intake {
        int PORT = 5;
        double SPEED = 0.9;
    }

    public interface Indexer {
        int FLOOR = 6;
        int UP = 7;
        double FLOOR_SPEED = 0.3;
        double UP_SPEED = 0.85;
    }

    public interface Pneumatics {
        int SOLENOID_1 = 0;
        int SOLENOID_2 = 1;
    }

    public interface Shooter {
        int PORT = 9;
        int SLAVE_PORT = 10;
        double F = 0.05;
        double P = 0;
        double I = 0;
        double D = 0;
        double ENCODER_TICKS_PER_MOTOR_REVOLUTION = 2048.0;
        double RAMP_SECONDS = 1.5;
        double MAIN_LOW_SPEED = 0.33;
        double MAIN_HIGH_SPEED = 0.95;
        double SLAVE_LOW_SPEED = 0.33;
        double SLAVE_HIGH_SPEED = 0.95;
    }

    public interface Limelight {
        double LIMELIGHT_HEIGHT = 0;
        double VERTICAL_HUB_HEIGHT = 0;
        double LIMELIGHT_ANGLE = 0;
    }

    public interface Climber {
        int PORT = 8;
        double UP_SPEED = 0.7;
        double DOWN_SPEED = 0.3;
    }
  
    public interface Controller {
        int DRIVER_PORT = 0;
        int OPERATOR_PORT = 1;
        double CONTROLLER_DRIFT = 0;
    }
}
