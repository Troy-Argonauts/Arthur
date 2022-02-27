// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class I_StartIndexer extends SequentialCommandGroup {
  public I_StartIndexer() {
    addCommands(
      new RunCommand(() -> Robot.getIntakeIndexer().activateFloor(), Robot.getIntakeIndexer())
    );
    addRequirements(Robot.getIntakeIndexer());
  }
}
