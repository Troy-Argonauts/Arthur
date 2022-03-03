package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;


public class DT_MoveUsingEncoders extends SequentialCommandGroup {

    public double revolutions;
    public DT_MoveUsingEncoders(double revolutions) {
        addRequirements(Robot.getDriveTrain());

        this.revolutions = revolutions;
    }

    @Override
    public void initialize() {
        Robot.getDriveTrain().zeroEncoders();
    }

    @Override
    public void execute() {
        Robot.getDriveTrain().zeroEncoders();

        if (revolutions > 0) {
            if (Robot.getDriveTrain().getLocation() < revolutions) {
                Robot.getDriveTrain().cheesyDrive(0, 0.1);
            } else {
                end(true);
            }
        } else if (revolutions < 0) {
            if (Robot.getDriveTrain().getLocation() > revolutions) {
                Robot.getDriveTrain().cheesyDrive(0, -0.1);
            } else {
                end(true);
            }
        } else {
            end(true);
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.getDriveTrain().cheesyDrive(0,0);
    }
}
