package frc.robot.auton.routines;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.auton.commands.*;
import frc.robot.commands.ShootingSequence;

public class TwoBall extends SequentialCommandGroup {
    public TwoBall() {
        addCommands(
                new DT_ResetSensors(),

                new I_StartIntake(),
                new DT_DriveStraight(44,0.5, 0.2),

                new I_StopIntake(),
                new WaitCommand(1),
                new InstantCommand(() -> Robot.getDriveTrain().turnToAngle(180)),

                new WaitCommand(1),
                new DT_DriveStraight(110, 2, 0.25),

                new InstantCommand(() -> Robot.getDriveTrain().cheesyDriveAuton(0,-1, 0.2)),
                new WaitCommand(0.15),
                new InstantCommand(() -> Robot.getDriveTrain().cheesyDriveAuton(0,0, 0)),

                new InstantCommand(() -> Robot.getShooter().resetPreset(1)),
                new ShootingSequence()
        );
        addRequirements(Robot.getDriveTrain(), Robot.getShooter(), Robot.getIntake(), Robot.getIndexer(), Robot.getPneumaticsSystem());
    }
}