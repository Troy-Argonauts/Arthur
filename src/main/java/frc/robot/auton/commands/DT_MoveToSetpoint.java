package frc.robot.auton.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.Robot;

public class DT_MoveToSetpoint extends PIDCommand {

    public DT_MoveToSetpoint(double setpoint) {
        super(
            new PIDController(Constants.DT_kP, Constants.DT_kI, Constants.DT_kD),
            Robot.getDriveTrain()::getLocation,
            setpoint,
            output -> Robot.getDriveTrain().cheesyDrive(0, output),
            Robot.getDriveTrain()
        );
        getController().setTolerance(Constants.DT_PIDTolerance);
    }

    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}