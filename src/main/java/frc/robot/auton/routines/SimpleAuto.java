// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;

public class SimpleAuto extends SequentialCommandGroup {

  public SimpleAuto() {
    addCommands(
      new DT_ZeroEncoders(),
      new RunCommand(() -> Robot.getShooter().activate(), Robot.getShooter()).withTimeout(2),

      // Pick up ball
      new I_StartIntake(),
      new DT_MoveToSetpoint(-1),
      new I_StopIntake(),

      new RunCommand(() -> Robot.getIntakeIndexer().toggle(), Robot.getIntakeIndexer()).withTimeout(1)
      );
    addRequirements(Robot.getDriveTrain(), Robot.getIntake(), Robot.getIntakeIndexer(), Robot.getShooter(), Robot.getPneumaticsSystem());
  }
}