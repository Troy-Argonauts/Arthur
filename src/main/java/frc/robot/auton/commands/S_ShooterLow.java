// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class S_ShooterLow extends SequentialCommandGroup {
  public S_ShooterLow() {
    addCommands(
            new InstantCommand(() -> Robot.getShooter().setState(Shooter.ShooterState.LOW), Robot.getShooter()),
            new WaitCommand(1),
            new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.IN, Indexer.Motor.UP), Robot.getIndexer()),
            new WaitCommand(2),
            new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED), Robot.getIndexer()),
            new InstantCommand(() -> Robot.getShooter().setState(Shooter.ShooterState.STOPPED), Robot.getShooter())
    );
    addRequirements(Robot.getShooter());
  }
}