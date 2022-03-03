package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.DT_MoveToSetpoint;
import frc.robot.auton.commands.DT_MoveUsingEncoders;


public class Move extends SequentialCommandGroup {

	public Move() {
        addCommands(
                new DT_MoveToSetpoint(-15)
        );
        addRequirements(Robot.getDriveTrain());
    }

}
