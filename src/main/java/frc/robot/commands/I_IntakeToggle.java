package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class I_IntakeToggle extends CommandBase {

    public I_IntakeToggle() {
        addRequirements(Robot.getIntake());
    }

    @Override
    public void execute() {
        Robot.getIntake().toggle();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
