package frc.robot.auton.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.Robot;

public class DT_MoveToSetpoint extends PIDCommand {

    public DT_MoveToSetpoint(double setpoint) {
        super(
            new PIDController(Constants.DriveTrain.DT_kP, Constants.DriveTrain.DT_kI, Constants.DriveTrain.DT_kD),
            Robot.getDriveTrain()::getLocation,
            -setpoint,
            output -> Robot.getDriveTrain().cheesyDrive(0, output, 0.7),
            Robot.getDriveTrain()
        );
        getController().setTolerance(Constants.DriveTrain.DT_PIDTolerance);
    }

    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}