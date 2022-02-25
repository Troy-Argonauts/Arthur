// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // CAN Ports

    // DriveTrain Ports
    public static int DT_FRONT_RIGHT = 3;
    public static int DT_FRONT_LEFT = 1;
    public static int DT_REAR_RIGHT = 4;
    public static int DT_REAR_LEFT = 2;

    // Intake Ports
    public static int I_INTAKE = 5;
    public static I2C.Port[] INDEXER_SENSORS = new I2C.Port[2];
    //Sensor Port TBD

    // Indexer Ports
    public static int I_INDEXER_FLOOR = 6;
    public static int I_INDEXER_UP = 7;

    // Shifter Ports
    public static int INTAKE_SHIFTER_1 = 0;
    public static int INTAKE_SHIFTER_2 = 1;

    // Shooter Ports
    public static int SHOOTER = 9;

    // Monkey Bars Ports
    public static int MONKEY_BARS = 8;

    // Controller Variables
    public static int DRIVER_PORT = 0;
    public static int OPERATOR_PORT = 1;
    public static double CONTROLLER_DRIFT = 0.01;

    // Limelight Variables
    public static double LIMELIGHT_HEIGHT = 0;
    public static double VERTICAL_HUB_HEIGHT = 0;
    public static double LIMELIGHT_ANGLE = 0;

    // Drive Train Variables
    public static double DT_kWheelDiameterMeters = 0.2;
    public static double DT_kEncoderCPR = 2048;
    public static double DT_kEncoderDistancePerPulse = (DT_kWheelDiameterMeters * Math.PI)/ DT_kEncoderCPR;
    public static double DT_kP = 0.1;
    public static double DT_kI = 0;
    public static double DT_kD = 0;
    public static double DT_PIDTolerance = 0.5;

    // Shooter Variables
    public static double SHOOTER_F = 0.05;
    public static double SHOOTER_P = 0;
    public static double SHOOTER_I = 0;
    public static double SHOOTER_D = 0;
    public static double ENCODER_TICKS_PER_MOTOR_REVOLUTION = 2048.0;

    // Indexer Variables
    public static int BOTTOMINDEXERSENSOR_PING = 1;
    public static int BOTTOMINDEXERSENSOR_RESPONSE = 2;
}
