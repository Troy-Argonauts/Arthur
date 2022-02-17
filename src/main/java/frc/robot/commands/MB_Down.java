package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;


public class MB_Down extends InstantCommand {

    public MB_Down() {
        addRequirements(Robot.getMonkeyBars());
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.getMonkeyBars().down();
    }

    @Override
    public void end(boolean interrupted) {

    }
}
