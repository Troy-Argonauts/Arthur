// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class I_StopIntake extends ParallelCommandGroup {

    public I_StopIntake() {
        addCommands(
            new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.STOPPED), Robot.getIntake()),
            new InstantCommand(Robot.getPneumaticsSystem()::pickupIntake),
            new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED, Indexer.Motor.FLOOR))
        );
        addRequirements(Robot.getPneumaticsSystem(), Robot.getIntake());
    }
}