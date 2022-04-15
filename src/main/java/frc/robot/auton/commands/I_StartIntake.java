// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class I_StartIntake extends ParallelCommandGroup {
    public I_StartIntake() {
        addCommands(
                new InstantCommand(Robot.getPneumaticsSystem()::dropIntake),
                new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.IN), Robot.getIntake()),
                new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.IN, Indexer.Motor.FLOOR))
        );
        addRequirements(Robot.getPneumaticsSystem(), Robot.getIntake());
    }
}