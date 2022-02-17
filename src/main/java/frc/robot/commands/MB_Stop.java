package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;


public class MB_Stop extends InstantCommand {

    public MB_Stop() {
        addRequirements(Robot.getMonkeyBars());
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.getMonkeyBars().stop();
    }

    @Override
    public void end(boolean interrupted) {

    }
}
