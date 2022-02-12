package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class I_IntakeForward extends CommandBase {

    public I_IntakeForward() {
        addRequirements(Robot.getIntake());
    }

    @Override
    public void execute() {
        Robot.getIntake().executeForward();
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
