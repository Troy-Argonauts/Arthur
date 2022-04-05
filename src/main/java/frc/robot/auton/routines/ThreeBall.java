// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ThreeBall extends SequentialCommandGroup {
  /** Creates a new ThreeBall. */
  public ThreeBall() {
    addCommands(
        new InstantCommand(Robot.getDriveTrain()::zeroEncoders),

        new S_ShooterHigh(),

        new DT_TurnToAngle(180),
        new I_StartIntake(),
        new RunCommand(() -> Robot.getDriveTrain().driveStraight(15)),

        new DT_TurnToAngle(-70),
        new RunCommand(() -> Robot.getDriveTrain().driveStraight(20)),
        new I_StopIntake(),

        new DT_TurnToAngle(20),
        new RunCommand(() -> Robot.getDriveTrain().driveStraight(15)),

        new S_ShooterHigh()
    );
  }
}
