package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Bobot;

import java.util.Set;

public class DT extends CommandBase {

    public DT() {
        addRequirements(Bobot.driveTrain);
    }

    @Override
    public void execute() {
        Bobot.driveTrain.cheesyDrive(Bobot.robotContainer.controller.getLeftJoystickY(), Bobot.robotContainer.controller.getRightJoystickX());
    }

    @Override
    public void end(boolean interrupted) {
        Bobot.driveTrain.cheesyDrive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
