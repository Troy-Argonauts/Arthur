// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;

public class SimpleAuto extends SequentialCommandGroup {

  public SimpleAuto() {
    addCommands(
      new DT_ZeroEncoders(),
      new I_StartIndexer(),
      new S_ActivateShooter(),
      new S_DeactivateShooter(),

      // Pick up ball
      new I_StartIntake(),
      new DT_MoveToSetpoint(-1),
      new I_StopIntake(),
      new I_StopIndexer(),

      new DT_MoveToSetpoint(1),
      new S_ActivateShooter(),
      new S_DeactivateShooter()
    );
    addRequirements(Robot.getDriveTrain(), Robot.getIntake(), Robot.getIntakeIndexer(), Robot.getShooter(), Robot.getPneumaticsSystem());
  }
}
