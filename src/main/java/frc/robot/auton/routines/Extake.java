package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;


public class Extake extends SequentialCommandGroup {

    public Extake() {
        addCommands(
            new DT_ZeroEncoders(),
            new I_ReverseIntake(),
            new DT_MoveToSetpoint(-1),

            new I_StopIntake(),
            new I_StopIndexer()
        );
        addRequirements(Robot.getPneumaticsSystem(), Robot.getIntake(), Robot.getIntakeIndexer(), Robot.getDriveTrain());
    }
}
