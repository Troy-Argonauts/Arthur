package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;


public class I_ReverseIntake extends ParallelCommandGroup {

    public I_ReverseIntake() {
        addCommands(
                new InstantCommand(Robot.getPneumaticsSystem()::dropIntake),
                new InstantCommand(Robot.getIntake()::backward),
                new InstantCommand(Robot.getIntakeIndexer()::activateFloorBackward),
                new InstantCommand(Robot.getIntakeIndexer()::activateUpBackward)
        );
        addRequirements(Robot.getIntake(), Robot.getPneumaticsSystem());
    }
}