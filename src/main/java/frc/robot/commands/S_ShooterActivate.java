package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class S_ShooterActivate extends CommandBase {

    public S_ShooterActivate() {
        addRequirements(Robot.getShooter());
    }

    @Override
    public void execute() {
        Robot.getShooter().activate();
    }
}
