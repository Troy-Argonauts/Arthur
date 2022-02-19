// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class S_ActivateShooter extends CommandBase {

  public S_ActivateShooter() {
    addRequirements(Robot.getShooter());
  }

  
  @Override
  public void initialize() {
    withTimeout(2);
  }

  @Override
  public void execute() {
    Robot.getShooter().activate();
  }

  @Override
  public void end(boolean interrupted) {
    Robot.getShooter().stop();
  }

}
