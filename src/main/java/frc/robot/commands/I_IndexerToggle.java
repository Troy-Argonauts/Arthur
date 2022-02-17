// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

public class I_IndexerToggle extends InstantCommand {

  public I_IndexerToggle() {
    addRequirements(Robot.getIntakeIndexer());
  }

  @Override
  public void execute() {
    Robot.getIntakeIndexer().toggle();
  }

  @Override
  public void end(boolean interrupted) {}
}
