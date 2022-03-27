// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;

public class Shoot3 extends SequentialCommandGroup {

  public Shoot3() {
    addCommands(
      new DT_ZeroEncoders(),
      new S_ShooterHigh(),

      new I_StartIndexer(),
      new I_StartIntake(),
      new DT_TurnToTarget(-1, 1),
      new DT_MoveToSetpoint(1),

      new DT_TurnToTarget(-1, 0.5),
      new DT_MoveToSetpoint(1),

      new I_StopIntake(),

      new DT_TurnToTarget(1, 0.5),
      new DT_MoveToSetpoint(1),
      new S_ShooterHigh()
    );
    addRequirements(Robot.getDriveTrain(), Robot.getIntake(), Robot.getIndexer(), Robot.getShooter(), Robot.getPneumaticsSystem());
  }
}
