// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DT_MoveToAngle extends CommandBase {
    double angle;
  /** Creates a new DT_MoveToAngle. */
  public DT_MoveToAngle(double angle) {
    addRequirements(Robot.getDriveTrain());
    double out = (angle < -180) ? angle + 360 : angle;
    this.angle = (out > 180) ? out - 360 : out;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(Robot.getDriveTrain().getAngle() - angle) < 180) {
        if (Robot.getDriveTrain().getAngle() > angle) {
            Robot.getDriveTrain().cheesyDrive(-0.2, 0, 0.5);
        } else if (Robot.getDriveTrain().getAngle() < angle) {
            Robot.getDriveTrain().cheesyDrive(0.2, 0, 0.5);
        } else {
            end(true);
        }
    } else {
        if (Robot.getDriveTrain().getAngle() < angle) {
            Robot.getDriveTrain().cheesyDrive(0.2, 0, 0.5);
        } else if (Robot.getDriveTrain().getAngle() < angle) {
            Robot.getDriveTrain().cheesyDrive(-0.2, 0, 0.5);
        } else {
            end(true);
        } 
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.getDriveTrain().cheesyDrive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
