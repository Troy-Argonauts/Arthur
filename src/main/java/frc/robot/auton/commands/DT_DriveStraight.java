package frc.robot.auton.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class DT_DriveStraight extends SequentialCommandGroup {
    public DT_DriveStraight(double inches, double waitTime, double speed) {
        addCommands(
                new DT_ResetSensors(),
                new InstantCommand(() -> Robot.getDriveTrain().driveStraight(inches, speed)),
                new WaitCommand(waitTime)
                );
        addRequirements(Robot.getDriveTrain());
    }
}