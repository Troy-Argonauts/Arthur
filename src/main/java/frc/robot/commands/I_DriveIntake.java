package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class I_DriveIntake extends CommandBase {

    public I_DriveIntake() {
        addRequirements(Robot.intake);
    }

    @Override
    public void execute() {
        
    }
}
