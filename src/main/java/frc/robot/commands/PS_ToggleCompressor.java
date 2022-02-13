package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class PS_ToggleCompressor extends CommandBase {

    public PS_ToggleCompressor() {
        addRequirements(Robot.getPneumaticsSystem());
    }

    @Override
    public void execute() {
        Robot.getPneumaticsSystem().toggleCompressor();
    }

    @Override
    public void end(boolean interrupted) {
        Robot.getPneumaticsSystem().stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
