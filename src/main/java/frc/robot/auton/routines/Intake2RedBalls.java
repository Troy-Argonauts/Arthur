package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;


public class Intake2RedBalls extends SequentialCommandGroup {

    public Intake2RedBalls() {
        addCommands(
                new DT_ZeroEncoders(),
                new S_ShooterHigh(),

                new DT_TurnToTarget(-1,1),
                new I_StartIntake(),
                new I_StartIndexer(),
                new DT_MoveToSetpoint(1),

                new DT_TurnToTarget(1,0.5),
                new DT_MoveToSetpoint(2),

                new DT_TurnToTarget(-1,0.25),
                new DT_MoveToSetpoint(1),

                new I_ReverseIntake(),
                new I_StopIntake(),
                new I_StopIndexer()
        );
        addRequirements(Robot.getIntake(), Robot.getDriveTrain(), Robot.getIntakeIndexer(), Robot.getPneumaticsSystem(), Robot.getShooter());
    }

}
