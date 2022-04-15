// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

    public interface DriveTrain {
        // DriveTrain Ports
        int FRONT_RIGHT = 3;
        int FRONT_LEFT = 1;
        int REAR_RIGHT = 4;
        int REAR_LEFT = 2;
        double WHEEL_DIAMETER_INCHES = 6.0;
        int ENCODER_NU_PER_WHEEL_REVOLUTION = 17161;
        double WHEEL_REVOLUTION_DISTANCE_INCHES = WHEEL_DIAMETER_INCHES * Math.PI;
        double INCHES_PER_NU = WHEEL_REVOLUTION_DISTANCE_INCHES / ENCODER_NU_PER_WHEEL_REVOLUTION;
        double NU_PER_INCH = ENCODER_NU_PER_WHEEL_REVOLUTION / WHEEL_REVOLUTION_DISTANCE_INCHES;
        double kP = 0.02;
        double kI = 0;
        double kD = 0;
        double kP_TURN = 0.1;
        double kI_TURN = 0;
        double kD_TURN = 0;
        double TURN_TOLERANCE_DEGREES = 1.0;
        double PID_TOLERANCE = 0.5;
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
        double UP_SPEED = 0.95;
    }

    public interface Pneumatics {
        int SOLENOID_1 = 0;
        int SOLENOID_2 = 1;
    }

    public interface Shooter {
        int PORT = 9;
        int SLAVE_PORT = 10;
        double kF = 0.05;
        double kP = 0;
        double kI = 0;
        double kD = 0;
        double RAMP_SECONDS = 1.5;
        double FRONT_DEFAULT_SPEED = 0.33;
        double BACK_DEFAULT_SPEED = 0.33;
    }

    public interface Limelight {
        double LIMELIGHT_HEIGHT = 0;
        double VERTICAL_HUB_HEIGHT = 0;
        double LIMELIGHT_ANGLE = 0;
    }

    public interface Climber {
        int PORT = 8;
        double UP_SPEED = 0.7;
        double DOWN_SPEED = 0.7;
    }
  
    public interface Controller {
        int DRIVER_PORT = 0;
        int OPERATOR_PORT = 1;
        double CONTROLLER_DRIFT = 0.05;
    }
}
