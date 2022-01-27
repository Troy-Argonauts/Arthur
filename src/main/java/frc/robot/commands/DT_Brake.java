package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DT_Brake extends CommandBase {


    @Override
    public void execute() {
        Robot.driveTrain.breakMode();
    }
}
