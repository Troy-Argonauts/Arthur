package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;


public class ShootAndPush extends SequentialCommandGroup {

    public ShootAndPush() {
        addCommands(
                new DT_ZeroEncoders(),
                new S_Shooter(),
                new DT_TurnToTarget(1,0.75),
                new DT_MoveToSetpoint(33)
        );
        addRequirements(Robot.getDriveTrain(), Robot.getShooter(), Robot.getIntakeIndexer());
    }
}
