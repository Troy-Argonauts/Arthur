// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

public class DT_TurnToTarget extends SequentialCommandGroup {

  public DT_TurnToTarget(int direction, double time) {
    addCommands(
      new RunCommand(() -> Robot.getDriveTrain().cheesyDrive(0, 0.5 * direction), Robot.getDriveTrain()).withTimeout(time)
    );
  }
}
