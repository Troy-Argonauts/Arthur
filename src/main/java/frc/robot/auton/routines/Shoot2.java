// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.auton.commands.*;

public class Shoot2 extends SequentialCommandGroup {

  public Shoot2() {
    addCommands(
            new DT_ZeroEncoders(),

            new I_StartIndexer(),
            new I_StartIntake(),
            new WaitCommand(1),
            new DT_MoveToSetpoint(23),
            new DT_TurnToTarget(-1,1),
            new I_StopIntake(),

            new DT_MoveToSetpoint(35),
            new S_ShooterHigh()
    );
    addRequirements(Robot.getDriveTrain(), Robot.getIntake(), Robot.getIndexer(), Robot.getShooter(), Robot.getPneumaticsSystem());
  }
}