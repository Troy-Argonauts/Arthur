// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Robot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class I_StartIntake extends ParallelCommandGroup {
  public I_StartIntake() {
    addCommands(
      new RunCommand(() -> Robot.getPneumaticsSystem().dropIntake(), Robot.getPneumaticsSystem()).withTimeout(1), 
      new RunCommand(() -> Robot.getIntake().forward(), Robot.getIntake())
    );
    addRequirements(Robot.getPneumaticsSystem(), Robot.getIntake());
  }
}
