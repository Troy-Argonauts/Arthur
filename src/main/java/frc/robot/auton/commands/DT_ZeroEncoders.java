// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;

public class DT_ZeroEncoders extends ParallelCommandGroup {
  public DT_ZeroEncoders() {
    addCommands(
      new InstantCommand(Robot.getDriveTrain()::zeroEncoders)
    );
    addRequirements(Robot.getDriveTrain());
  }
}
