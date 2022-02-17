package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;


public class MB_Up extends InstantCommand {

    public MB_Up() {
        addRequirements(Robot.getMonkeyBars());
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.getMonkeyBars().up();
    }

    @Override
    public void end(boolean interrupted) {

    }
}
