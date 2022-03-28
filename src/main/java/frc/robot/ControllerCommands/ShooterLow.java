// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.ControllerCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShooterLow extends SequentialCommandGroup {
  /** Creates a new ShooterLow. */
    public ShooterLow() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
        addCommands(
            new InstantCommand(() -> Robot.getShooter().setState(Shooter.ShooterState.LOW), Robot.getShooter()),
            new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.STOPPED), Robot.getIntake()),
            new InstantCommand(Robot.getPneumaticsSystem()::pickupIntake),
            new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED), Robot.getIndexer()),
            new WaitCommand(1),
            new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.IN), Robot.getIndexer()),
            new WaitCommand(1),
            new ParallelCommandGroup(
                new InstantCommand(() -> Robot.getShooter().setState(Shooter.ShooterState.STOPPED), Robot.getShooter()),
                new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED), Robot.getIndexer())
            )
        );
    }
}
