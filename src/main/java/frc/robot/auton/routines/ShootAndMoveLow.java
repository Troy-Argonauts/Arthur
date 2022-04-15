// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.routines;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auton.commands.DT_ResetSensors;
import frc.robot.auton.commands.S_ShooterLow;

public class ShootAndMoveLow extends SequentialCommandGroup {

    public ShootAndMoveLow() {
        addCommands(
                new DT_ResetSensors(),

                new S_ShooterLow(),
                new RunCommand(() -> Robot.getDriveTrain().driveStraight(-120, true))
        );
        addRequirements(Robot.getDriveTrain(), Robot.getShooter(), Robot.getIntake(), Robot.getIndexer(), Robot.getPneumaticsSystem());
    }
}