// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class S_DeactivateShooter extends SequentialCommandGroup {
  public S_DeactivateShooter() {
    addCommands(
      new RunCommand(() -> Robot.getShooter().stop(), Robot.getShooter())
    );
  }
}
