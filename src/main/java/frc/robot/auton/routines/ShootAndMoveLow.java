// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.DT_MoveToSetpoint;
import frc.robot.auton.commands.DT_ZeroEncoders;
import frc.robot.auton.commands.S_ShooterHigh;
import frc.robot.auton.commands.S_ShooterLow;

public class ShootAndMoveLow extends SequentialCommandGroup {

  public ShootAndMoveLow() {
    addCommands(
            new DT_ZeroEncoders(),
            new S_ShooterLow(),
            new DT_MoveToSetpoint(-40)
    );
    addRequirements(Robot.getDriveTrain(), Robot.getShooter(), Robot.getIntakeIndexer());
  }
}