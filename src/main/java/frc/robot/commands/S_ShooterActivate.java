package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class S_ShooterActivate extends CommandBase {

    public S_ShooterActivate() {
        addRequirements(Robot.getShooter());
    }

    @Override
    public void execute() {
        Robot.getShooter().activate();
    }
}
