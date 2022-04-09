package frc.robot.auton.routines;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;

public class TwoBall extends SequentialCommandGroup {
    public TwoBall() {
        addCommands(
                new DT_ResetSensors(),

//                new I_StartIntake(),
//                new RunCommand(() -> Robot.getDriveTrain().driveStraight(60)),
//
//                new I_StopIntake(),
                new InstantCommand(() -> Robot.getDriveTrain().turnToAngle(180))
//                new RunCommand(() -> Robot.getDriveTrain().driveStraight(60)),
//
//                new S_ShooterLow()
        );
        addRequirements(Robot.getDriveTrain(), Robot.getShooter(), Robot.getIntake(), Robot.getIndexer(), Robot.getPneumaticsSystem());
    }
}