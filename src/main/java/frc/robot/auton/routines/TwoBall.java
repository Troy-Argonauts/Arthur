package frc.robot.auton.routines;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.DT_TurnToAngle;
import frc.robot.auton.commands.I_StartIntake;
import frc.robot.auton.commands.I_StopIntake;
import frc.robot.auton.commands.S_ShooterHigh;

public class TwoBall extends SequentialCommandGroup {
    public TwoBall() {
        addCommands(
                new InstantCommand(Robot.getDriveTrain()::zeroEncoders),

                new I_StartIntake(),
                new RunCommand(() -> Robot.getDriveTrain().driveStraight(15)),

                new DT_TurnToAngle(180),
                new I_StopIntake(),
                new RunCommand(() -> Robot.getDriveTrain().driveStraight(15)),

                new S_ShooterHigh()
        );
        addRequirements(Robot.getDriveTrain(), Robot.getShooter(), Robot.getIntake(), Robot.getIndexer(), Robot.getPneumaticsSystem());
    }
}