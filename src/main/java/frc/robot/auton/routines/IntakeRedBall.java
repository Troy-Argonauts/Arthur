package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;



public class IntakeRedBall extends SequentialCommandGroup {

    public IntakeRedBall() {
        addCommands(
                new DT_ZeroEncoders(),

                new I_StartIntake(),
                new I_StopIndexer(),
                new DT_TurnToTarget(-1,1),
                new DT_MoveToSetpoint(1),
                new I_StopIndexer(),
                new I_StopIntake()

        );
        addRequirements(Robot.getDriveTrain(), Robot.getIntake(), Robot.getPneumaticsSystem());
    }

}
