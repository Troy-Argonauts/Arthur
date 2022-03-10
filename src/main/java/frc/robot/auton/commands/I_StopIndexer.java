// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
public class I_StopIndexer extends SequentialCommandGroup {
  public I_StopIndexer() {
    addCommands(
            new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor),
            new InstantCommand(Robot.getIntakeIndexer()::deactivateUp)
    );
    addRequirements(Robot.getIntakeIndexer());
  }
}