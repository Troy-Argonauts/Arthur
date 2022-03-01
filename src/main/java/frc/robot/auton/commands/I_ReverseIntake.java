package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Robot;


public class I_ReverseIntake extends ParallelCommandGroup {

    public I_ReverseIntake() {
        addCommands(
                new RunCommand(() -> Robot.getPneumaticsSystem().dropIntake(), Robot.getPneumaticsSystem()),
                new RunCommand(() -> Robot.getIntake().backward(), Robot.getIntake()),
                new RunCommand(() -> Robot.getIntakeIndexer().activateFloorBackward(), Robot.getIntakeIndexer()),
                new RunCommand(() -> Robot.getIntakeIndexer().activateUpBackward(), Robot.getIntakeIndexer())
        );
        addRequirements(Robot.getIntake(), Robot.getPneumaticsSystem());
    }
}
