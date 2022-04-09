package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    /** 
     * Creates all 2 main motors of the robot
     */

    private final TalonFX frontLeft, frontRight, rearLeft, rearRight;
    private final ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

    /**
     * Sets the values of the frontLeft and frontRight motors, and creates local rear motors.
     * Has rear motors follow front motors, and sets all motors to coast when stopped.
     */
    public DriveTrain() {
        frontLeft = new TalonFX(Constants.DriveTrain.FRONT_LEFT);
        frontRight = new TalonFX(Constants.DriveTrain.FRONT_RIGHT);
        rearLeft = new TalonFX(Constants.DriveTrain.REAR_LEFT);
        rearRight = new TalonFX(Constants.DriveTrain.REAR_RIGHT);

        frontLeft.setSensorPhase(false);
        frontRight.setSensorPhase(false);
        rearLeft.setSensorPhase(false);
        rearRight.setSensorPhase(false);

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        rearLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);
        rearRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 50);

        frontRight.configFeedbackNotContinuous(false, 4);
        frontLeft.configFeedbackNotContinuous(false, 4);
        rearLeft.configFeedbackNotContinuous(false, 4);
        rearRight.configFeedbackNotContinuous(false, 4);

        frontRight.configOpenloopRamp(Constants.DriveTrain.RAMP_SECONDS);
        frontLeft.configOpenloopRamp(Constants.DriveTrain.RAMP_SECONDS);
        rearRight.configOpenloopRamp(Constants.DriveTrain.RAMP_SECONDS);
        rearLeft.configOpenloopRamp(Constants.DriveTrain.RAMP_SECONDS);
        frontRight.configClosedloopRamp(Constants.DriveTrain.RAMP_SECONDS);
        frontLeft.configClosedloopRamp(Constants.DriveTrain.RAMP_SECONDS);
        rearRight.configClosedloopRamp(Constants.DriveTrain.RAMP_SECONDS);
        rearLeft.configClosedloopRamp(Constants.DriveTrain.RAMP_SECONDS);

        frontRight.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
        frontLeft.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);

        rearLeft.follow(frontLeft);
        rearRight.follow(frontRight);

        frontLeft.setInverted(false);
        rearLeft.setInverted(InvertType.FollowMaster);
        frontRight.setInverted(true);
        rearRight.setInverted(InvertType.FollowMaster);

        frontLeft.setNeutralMode(NeutralMode.Coast);
        frontRight.setNeutralMode(NeutralMode.Coast);
        rearLeft.setNeutralMode(NeutralMode.Coast);
        rearRight.setNeutralMode(NeutralMode.Coast);

        gyro.calibrate();
    }

    /**
     * Has the robot move at a certain speed, but allows the robot to turn, using input from Joysticks
     * @param turn Amount to turn the robot
     * @param speed Speed of robot
     */
    public void cheesyDrive(double turn, double speed, double nerf) {
        frontLeft.set(ControlMode.PercentOutput, -(speed - turn) * nerf);
        frontRight.set(ControlMode.PercentOutput, -(speed + turn) * nerf);
    }

    public double getLocation() {
        return Constants.DriveTrain.INCHES_PER_NU * (frontRight.getSelectedSensorPosition() + frontLeft.getSelectedSensorPosition()) / 2;
    }

    public double getEncoderPosition() {
        return ((frontRight.getSelectedSensorPosition() + frontLeft.getSelectedSensorPosition()) / 2);
    }

    public void zeroEncoders() {
        frontRight.setSelectedSensorPosition(0);
        frontLeft.setSelectedSensorPosition(0);
        rearRight.setSelectedSensorPosition(0);
        rearLeft.setSelectedSensorPosition(0);
    }

    public double getAngle() {
        return (gyro.getAngle() % 360);
    }

    public void zeroGyro() {
        gyro.reset();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Right Encoders", frontRight.getSelectedSensorPosition());
        SmartDashboard.putNumber("Left Encoders", frontLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("encoder", getEncoderPosition());

        SmartDashboard.putNumber("Angle", gyro.getAngle());
    }

    public void driveStraight(double inches) {
        double turningValue = (0 - gyro.getAngle()) * Constants.DriveTrain.kP_TURN;
        // Invert the direction of the turn if we are going backwards
        double distance = inches * Constants.DriveTrain.NU_PER_INCH;
        SmartDashboard.putNumber("Distance", distance);
        turningValue = Math.copySign(turningValue, distance);

        double finalTurningValue = turningValue;

        if (distance < 0) {
            while (getEncoderPosition() > distance) {
                cheesyDrive(finalTurningValue, -1, 0.1);
                SmartDashboard.putNumber("Right Encoders", frontRight.getSelectedSensorPosition());
                SmartDashboard.putNumber("Left Encoders", frontLeft.getSelectedSensorPosition());
                SmartDashboard.putNumber("encoder", getEncoderPosition());
            }
            cheesyDrive(0,0,1);
        } else if (distance > 0) {
            while (getEncoderPosition() < distance) {
                cheesyDrive(finalTurningValue, 1, 0.1);
                SmartDashboard.putNumber("Right Encoders", frontRight.getSelectedSensorPosition());
                SmartDashboard.putNumber("Left Encoders", frontLeft.getSelectedSensorPosition());
                SmartDashboard.putNumber("encoder", getEncoderPosition());
            }
            cheesyDrive(0,0,1);
        }
    }

    public void turnToAngle(int angle) {
        zeroEncoders();
        double encoderValue = angle * 5.322;
        
        frontRight.set(ControlMode.Position, encoderValue);
        frontLeft.set(ControlMode.Position, -encoderValue);
    }
}