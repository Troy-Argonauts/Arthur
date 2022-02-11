package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.commands.I_Intake;

public class I_Intake extends CommandBase {

    public I_Intake() {
        addRequirements(Robot.getIntake());
    }
}
