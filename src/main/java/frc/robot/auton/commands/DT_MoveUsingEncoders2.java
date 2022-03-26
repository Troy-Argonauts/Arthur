package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Robot;


public class DT_MoveUsingEncoders2 extends CommandBase {
    double revolutions;

    public DT_MoveUsingEncoders2(double revolutions) {
        addRequirements(Robot.getDriveTrain());

        this.revolutions = revolutions;
    }

    @Override
    public void initialize() {
        new RunCommand(Robot.getDriveTrain()::zeroEncoders);
    }

    @Override
    public void execute() {
        new RunCommand(Robot.getDriveTrain()::zeroEncoders);

        if (revolutions > 0) {
            if (Robot.getDriveTrain().getRevolutions() < revolutions) {
                Robot.getDriveTrain().cheesyDrive(0, 0.1, 0.7);
            } else {
                end(true);
            }
        } else if (revolutions < 0) {
            if (Robot.getDriveTrain().getRevolutions() > revolutions) {
                Robot.getDriveTrain().cheesyDrive(0, -0.1, 0.7);
            } else {
                end(true);
            }
        } else {
            end(true);
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.getDriveTrain().cheesyDrive(0,0, 0.7);
    }

}

