package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;


public class I_ReverseIntake extends ParallelCommandGroup {

    public I_ReverseIntake() {
        addCommands(
                new InstantCommand(Robot.getPneumaticsSystem()::dropIntake),
                new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.OUT), Robot.getIntake()),
                new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.OUT), Robot.getIndexer())
        );
        addRequirements(Robot.getIntake(), Robot.getPneumaticsSystem());
    }
}