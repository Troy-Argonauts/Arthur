// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.libs.util.Controller;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Controller driver;
  private final Controller operator;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driver = new Controller(Constants.DRIVER_PORT);
    operator = new Controller(Constants.OPERATOR_PORT);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   *
   *
   */
  private void configureButtonBindings() {
    Trigger rightTrigger = new Trigger( () -> operator.getRightTrigger() > 0);
    Trigger leftTrigger = new Trigger(() -> operator.getLeftTrigger() > 0 );

    // Driver Controls
    new RunCommand( () ->
            Robot.getDriveTrain().cheesyDrive(getDriver().getRightJoystickX(), driver.getLeftJoystickY()),
            Robot.getDriveTrain()
    ).execute();

    // Shooter Toggle
    operator.getXButton().toggleWhenPressed(
            new InstantCommand( () -> Robot.getShooter().toggle(), Robot.getShooter())
                    .alongWith(new InstantCommand( () -> Robot.getIntakeIndexer().toggleUp(), Robot.getIntakeIndexer()))
    );

    // Toggle Intake Power
    operator.getAButton().toggleWhenPressed(
            new InstantCommand( () -> Robot.getIntake().togglePower(), Robot.getIntake())
    );

    // Toggle Intake Direction
    operator.getYButton().toggleWhenPressed(
            new InstantCommand(
                    () -> Robot.getIntake().toggleDirection(),
                    Robot.getIntake()
            )
    );

    // Pneumatics Drop Intake
    operator.getRBButton().toggleWhenPressed(
            new InstantCommand(
                    () -> Robot.getPneumaticsSystem().dropIntake(),
                    Robot.getPneumaticsSystem())
    );

    // Pneumatics Pickup Intake
    operator.getLBButton().toggleWhenPressed(
            new InstantCommand(
                    () -> Robot.getPneumaticsSystem().pickupIntake(),
                    Robot.getPneumaticsSystem())
    );


    // Toggle Indexer Toggle
    operator.getBButton().toggleWhenPressed(
            new InstantCommand(
                    () -> Robot.getIntakeIndexer().toggleFloor(),
                    Robot.getIntakeIndexer()
            )
    );

    // Toggle Compressor
    operator.getSTARTButton().toggleWhenPressed(
            new InstantCommand(
                    () -> Robot.getPneumaticsSystem().toggleCompressor(),
                    Robot.getPneumaticsSystem()
            )
    );

    // MonkeyBars Up
    rightTrigger.whenActive(
            new InstantCommand(
                    () -> Robot.getMonkeyBars().up(),
                    Robot.getMonkeyBars()
            )
    ).whenInactive(
            new InstantCommand(
                    () -> Robot.getMonkeyBars().stop(),
                    Robot.getMonkeyBars()
            )
    );

    // MonkeyBars Down
    leftTrigger.whenActive(
            new InstantCommand(
                    () -> Robot.getMonkeyBars().down(),
                    Robot.getMonkeyBars()
            )
    ).whenInactive(
            new InstantCommand(
                    () -> Robot.getMonkeyBars().stop(),
                    Robot.getMonkeyBars()
            )
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public Controller getDriver() {
    return driver;
  }

  public Controller getOperator() {
    return operator;
  }
}
