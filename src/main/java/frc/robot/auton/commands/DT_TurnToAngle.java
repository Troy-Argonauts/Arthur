package frc.robot.auton.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Robot;
import frc.robot.Constants.DriveTrain;

public class DT_TurnToAngle extends PIDCommand {

  public DT_TurnToAngle(double angle) {
    super(
        new PIDController(DriveTrain.kTurnP, DriveTrain.kTurnI, DriveTrain.kTurnD),
        Robot.getDriveTrain()::getAngle,
        angle,
        output -> Robot.getDriveTrain().cheesyDrive(output, 0, 0.2),
        Robot.getDriveTrain());

    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(DriveTrain.kTurnToleranceDeg);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}