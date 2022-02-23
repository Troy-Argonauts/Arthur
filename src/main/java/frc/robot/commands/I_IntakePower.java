// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

public class I_IntakePower extends InstantCommand {
  public I_IntakePower() {
    addRequirements(Robot.getIntake());
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Robot.getIntake().togglePower();
  }

  @Override
  public void end(boolean interrupted) {}
}
