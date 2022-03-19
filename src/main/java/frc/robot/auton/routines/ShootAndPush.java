package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.DT_MoveToSetpoint;
import frc.robot.auton.commands.DT_TurnToTarget;
import frc.robot.auton.commands.DT_ZeroEncoders;
import frc.robot.auton.commands.S_ShooterHigh;


public class ShootAndPush extends SequentialCommandGroup {

    public ShootAndPush() {
        addCommands(
                new DT_ZeroEncoders(),
                new S_ShooterHigh(),
                new DT_TurnToTarget(1,0.75),
                new DT_MoveToSetpoint(33)
        );
        addRequirements(Robot.getDriveTrain(), Robot.getShooter(), Robot.getIntakeIndexer());
    }
}
