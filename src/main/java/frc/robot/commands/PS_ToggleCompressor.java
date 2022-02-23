package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

public class PS_ToggleCompressor extends InstantCommand {

  public PS_ToggleCompressor() {
    addRequirements(Robot.getPneumaticsSystem());
  }

  @Override
  public void initialize() {

  }
  
  @Override
  public void execute() {
    Robot.getPneumaticsSystem().toggleCompressor();
  }

  @Override
    public void end(boolean interrupted) {

  }

}