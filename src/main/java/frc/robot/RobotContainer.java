// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.libs.util.Controller;
import frc.libs.util.DPadButton;
import frc.robot.subsystems.Climber;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    public static Controller driver;
    public static Controller operator;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        driver = new Controller(Constants.Controller.DRIVER_PORT);
        operator = new Controller(Constants.Controller.OPERATOR_PORT);

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
        Robot.getDriveTrain().setDefaultCommand(
                new RunCommand(
                        () -> Robot.getDriveTrain().cheesyDrive(driver.getRightJoystickX(), driver.getLeftJoystickY()),
                        Robot.getDriveTrain()
                )
        );

        driver.getBButton().toggleWhenPressed(
                new InstantCommand(Robot.getIntake()::disable)
                        .alongWith(new InstantCommand(Robot.getShooter()::stop))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::deactivateUp))
        );

        operator.getAButton().toggleWhenPressed(
                // Deactivate intake motor in case
                new InstantCommand(Robot.getIntake()::disable)
                        // Pickup intake just in case
                        .andThen(new InstantCommand(Robot.getPneumaticsSystem()::pickupIntake))
                        // Deactivate floor in case
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
                        // Rev up to full speed
                        .andThen(new InstantCommand(Robot.getShooter()::highGoal))
                        // pause for 1 second
                        .andThen(new WaitCommand(1.5))
                        // Turn on up indexer (shoot 1st ball)
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateUpForward))
                        .andThen(new WaitCommand(0.25))
                        // Turn on floor indexer (shoot 2nd ball)
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateFloorForward))
                        .andThen(new WaitCommand(2))
                        // Turn Off all motors
                        .andThen(new InstantCommand(Robot.getShooter()::stop))
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateUp))
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
        );

        operator.getRBButton().toggleWhenPressed(
                new InstantCommand(
                        Robot.getPneumaticsSystem()::dropIntake)
                        .andThen(new InstantCommand(Robot.getIntake()::backward))
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateFloorForward))
        );

        operator.getLBButton().toggleWhenPressed(
                new InstantCommand(
                        Robot.getPneumaticsSystem()::pickupIntake)
                        .andThen(new InstantCommand(Robot.getIntake()::disable))
                        .andThen(new WaitCommand(1.25))
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
        );

        // Shooter Toggle
        operator.getXButton().toggleWhenPressed(
                // Deactivate intake motor in case
                new InstantCommand(Robot.getIntake()::disable)
                        // Pickup intake just in case
                        .andThen(new InstantCommand(Robot.getPneumaticsSystem()::pickupIntake))
                        // Deactivate floor in case
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
                        // Rev up to full speed
                        .andThen(new InstantCommand(Robot.getShooter()::lowGoal))
                        // pause for 1 second
                        .andThen(new WaitCommand(1.5))
                        // Turn on up indexer (shoot 1st ball)
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateUpForward))
                        .andThen(new WaitCommand(0.25))
                        // Turn on floor indexer (shoot 2nd ball)
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateFloorForward))
                        .andThen(new WaitCommand(2))
                        // Turn Off all motors
                        .andThen(new InstantCommand(Robot.getShooter()::stop))
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateUp))
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
        );

        // Toggle Intake Power
        driver.getAButton().toggleWhenPressed(
                new InstantCommand(Robot.getIntakeIndexer()::activateUpForward)
        );

        // Toggle Intake Direction
        operator.getYButton().toggleWhenPressed(
                new InstantCommand(Robot.getIntake()::toggleDirection)
        );
    }

    public Command getAutonomousCommand() {
        return null;
    }

    public static Controller getDriver() {
        return driver;
    }

    public static Controller getOperator() {
        return operator;
    }
}
