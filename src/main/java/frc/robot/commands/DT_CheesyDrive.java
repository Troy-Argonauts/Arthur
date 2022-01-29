package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DT_CheesyDrive extends CommandBase {

    public DT_CheesyDrive() {
        addRequirements(Robot.driveTrain);
    }

    @Override
    public void execute() {
        Robot.driveTrain.cheesyDrive(Robot.robotContainer.controller.getLeftJoystickY(), Robot.robotContainer.controller.getRightJoystickX());
    }

    @Override
    public void end(boolean interrupted) {
        Robot.driveTrain.cheesyDrive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
