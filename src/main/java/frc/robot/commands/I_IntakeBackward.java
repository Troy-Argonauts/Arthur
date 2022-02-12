package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class I_IntakeBackward extends CommandBase {
    
    public I_IntakeBackward() {
        addRequirements(Robot.getIntake());
    }

    @Override
    public void execute() {
        Robot.getIntake().executeBackward();
    }

    @Override
    public void end(boolean interrupted) {
        Robot.getIntake().activate(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
