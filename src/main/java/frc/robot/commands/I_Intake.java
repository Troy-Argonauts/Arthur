package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class I_Intake extends CommandBase {

    public I_Intake() {
        addRequirements(Robot.getIntake());
    }
}
