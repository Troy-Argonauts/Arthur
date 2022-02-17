package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DT_CheesyDrive extends CommandBase {

    public DT_CheesyDrive() {
        addRequirements(Robot.getDriveTrain());
    }

    @Override
    public void execute() {
        Robot.getDriveTrain().cheesyDrive(Robot.robotContainer.getDriver().getLeftJoystickX(), Robot.robotContainer.getDriver().getRightJoystickY()

        );
    }

    @Override
    public void end(boolean interrupted) {
        Robot.getDriveTrain().cheesyDrive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
