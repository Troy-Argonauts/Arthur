// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class S_ShooterHigh extends SequentialCommandGroup {
  public S_ShooterHigh() {
    addCommands(
            //new InstantCommand(Robot.getShooter()::highGoal),
            //new WaitCommand(0.5),
            new InstantCommand(Robot.getShooter()::highGoal),
            new WaitCommand(1),
            new InstantCommand(Robot.getIntakeIndexer()::activateUpForward),
            new WaitCommand(2),
            new InstantCommand(Robot.getIntakeIndexer()::deactivateUp),
            new InstantCommand(Robot.getShooter()::stop)
    );
    addRequirements(Robot.getShooter());
  }
}