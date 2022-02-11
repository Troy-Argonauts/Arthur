package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class I_Intake extends CommandBase {

    public I_Intake() {
        addRequirements(Robot.getIntake());
    }
    public void excecuteForward() {
        Robot.getIntake().activate(Robot.robotContainer.controller.getRightTrigger());
    }
    public void excecuteBackward() {
        Robot.getIntake().activate(-(Robot.robotContainer.controller.getLeftTrigger()));
    }
}
