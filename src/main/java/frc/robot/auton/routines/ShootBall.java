// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;

public class ShootBall extends SequentialCommandGroup {

  public ShootBall() {
    addCommands(
      new DT_ZeroEncoders(), 
      new S_ActivateShooter(), 
      new DT_MoveToSetpoint(-1)
    );
    addRequirements(Robot.getDriveTrain(), Robot.getShooter());
  }
}
