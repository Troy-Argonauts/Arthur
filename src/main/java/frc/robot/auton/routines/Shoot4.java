package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;


public class Shoot4 extends SequentialCommandGroup {

    public Shoot4() {
        addCommands(
                new DT_ZeroEncoders(),
                new S_ShooterHigh(),

                new DT_TurnToTarget(-1,1),
                new I_StartIntake(),
                new I_StartIndexer(),
                new DT_MoveToSetpoint(1),

                new DT_TurnToTarget(1,0.125),
                new DT_MoveToSetpoint(1.5),

                new DT_TurnToTarget(-1,1),
                new I_StopIntake(),
                new DT_MoveToSetpoint(2.5),

                new S_ShooterHigh(),
                new DT_TurnToTarget(-1,1.125),
                new I_StartIntake(),
                new I_StartIndexer(),

                new DT_MoveToSetpoint(1),
                new DT_TurnToTarget(-1,1),
                new I_StopIntake(),
                new DT_TurnToTarget(1,1),
                new S_ShooterHigh(),

                new I_ReverseIntake(),
                new I_StopIntake(),
                new I_StopIndexer()
        );
        addRequirements(Robot.getIntake(), Robot.getDriveTrain(), Robot.getIntakeIndexer(), Robot.getPneumaticsSystem(), Robot.getShooter());
    }

}
