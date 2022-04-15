package frc.robot.auton.routines;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.auton.commands.*;

public class TwoBall extends SequentialCommandGroup {
    public TwoBall() {
        addCommands(
                new DT_ResetSensors(),

                new I_StartIntake(),
                new InstantCommand(() -> Robot.getDriveTrain().driveStraight(44, false)),
                new WaitCommand(0.5),

                new I_StopIntake(),
                new WaitCommand(1),
                new InstantCommand(() -> Robot.getDriveTrain().turnToAngle(180)),

                new WaitCommand(1),
                new DT_ResetSensors(),
                new InstantCommand(() -> Robot.getDriveTrain().driveStraight(110, false)),

                new WaitCommand(1.75),
                new DT_ResetSensors(),
                new InstantCommand(() -> Robot.getDriveTrain().driveStraight(-3, true))

//                new S_ShooterLow()
        );
        addRequirements(Robot.getDriveTrain(), Robot.getShooter(), Robot.getIntake(), Robot.getIndexer(), Robot.getPneumaticsSystem());
    }
}