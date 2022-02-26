// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class I_StopIntake extends ParallelCommandGroup {

    public I_StopIntake() {
        addCommands(
                new RunCommand(() -> Robot.getIntake().disable(), Robot.getIntake()).withTimeout(2),
                new RunCommand(() -> Robot.getPneumaticsSystem().pickupIntake(), Robot.getPneumaticsSystem()).withTimeout(1)
        );
        addRequirements(Robot.getPneumaticsSystem(), Robot.getIntake());
    }
}