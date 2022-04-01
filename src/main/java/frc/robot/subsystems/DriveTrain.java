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
    private ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

    /**
     * Sets the values of the frontLeft and frontRight motors, and creates local rear motors.
     * Has rear motors follow front motors, and sets all motors to coast when stopped.
     */
    public DriveTrain() {
        frontLeft = new TalonFX(Constants.DriveTrain.DT_FRONT_LEFT);
        frontRight = new TalonFX(Constants.DriveTrain.DT_FRONT_RIGHT);
        rearLeft = new TalonFX(Constants.DriveTrain.DT_REAR_LEFT);
        rearRight = new TalonFX(Constants.DriveTrain.DT_REAR_RIGHT);

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

        frontRight.configOpenloopRamp(Constants.DriveTrain.DT_NEUTRALTORAMPSECONDS);
        frontLeft.configOpenloopRamp(Constants.DriveTrain.DT_NEUTRALTORAMPSECONDS);
        rearRight.configOpenloopRamp(Constants.DriveTrain.DT_NEUTRALTORAMPSECONDS);
        rearLeft.configOpenloopRamp(Constants.DriveTrain.DT_NEUTRALTORAMPSECONDS);
        frontRight.configClosedloopRamp(Constants.DriveTrain.DT_NEUTRALTORAMPSECONDS);
        frontLeft.configClosedloopRamp(Constants.DriveTrain.DT_NEUTRALTORAMPSECONDS);
        rearRight.configClosedloopRamp(Constants.DriveTrain.DT_NEUTRALTORAMPSECONDS);
        rearLeft.configClosedloopRamp(Constants.DriveTrain.DT_NEUTRALTORAMPSECONDS);

        frontRight.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
        frontLeft.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);

        rearLeft.follow(frontLeft);
        rearRight.follow(frontRight);

        frontLeft.setInverted(true);
        rearLeft.setInverted(InvertType.FollowMaster);
        frontRight.setInverted(false);
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
        frontLeft.set(ControlMode.PercentOutput, (speed - turn) * nerf);
        frontRight.set(ControlMode.PercentOutput, (speed + turn) * nerf);
    }

    public void tankDrive(double right, double left) {
        frontRight.set(ControlMode.PercentOutput, right);
        frontLeft.set(ControlMode.PercentOutput, left);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Front Right Position" , frontRight.getSelectedSensorPosition());
        SmartDashboard.putNumber("Front Left Position" , frontLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("Rear Right Position" , rearRight.getSelectedSensorPosition());
        SmartDashboard.putNumber("Rear Left Position" , rearLeft.getSelectedSensorPosition());
    }

    public double getLocation() {
        return Constants.DriveTrain.DT_kEncoderDistancePerPulse * (frontRight.getSelectedSensorPosition() + frontLeft.getSelectedSensorPosition())/2;
    }

    public double getRevolutions() {
        return ((frontRight.getSelectedSensorPosition() + frontLeft.getSelectedSensorPosition())/2) / Constants.DriveTrain.DT_kEncoderCPR;
    }

    public void zeroEncoders() {
        frontRight.setSelectedSensorPosition(0);
        frontLeft.setSelectedSensorPosition(0);
        rearRight.setSelectedSensorPosition(0);
        rearLeft.setSelectedSensorPosition(0);
    }

    public double getAngle() {
        double angle = -gyro.getAngle() % 360;
        double out = (angle < -180) ? angle + 360 : angle;
        return (out > 180) ? out - 360 : out;
    }

    public void zeroGyro() {
        gyro.reset();
    }
}