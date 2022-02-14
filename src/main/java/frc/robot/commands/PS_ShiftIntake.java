package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class PS_ShiftIntake extends CommandBase {
    
    public PS_ShiftIntake() {
        addRequirements(Robot.getPneumaticsSystem());
    }

    @Override
    public void execute() {
        Robot.getPneumaticsSystem().shiftIntake();
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
