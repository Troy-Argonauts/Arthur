// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.libs.util.Controller;
import frc.libs.util.DPadButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...

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
        Trigger operatorRightTrigger = new Trigger( () -> operator.getRightTrigger() > 0);
        Trigger operatorLeftTrigger = new Trigger(() -> operator.getLeftTrigger() > 0 );
        double ramp = 0.1;

        // Driver Controls
        Robot.getDriveTrain().setDefaultCommand(
                new RunCommand(() ->  {
//                            if (Math.abs(driver.getRightJoystickX()) > 0.2) {
//                                Robot.getDriveTrain().cheesyDrive(driver.getRightJoystickX(), driver.getLeftJoystickY(), 0.45);
//                            } else {
//                                Robot.getDriveTrain().cheesyDrive(driver.getRightJoystickX(), driver.getLeftJoystickY(), 0.7);
//                            }
                            Robot.getDriveTrain().cheesyDrive(driver.getRightJoystickX(), driver.getLeftJoystickY(), 0.7);
                        }, Robot.getDriveTrain())
        );

        driver.getBButton().toggleWhenPressed(
                new InstantCommand(Robot.getIntake()::disable)
                        .alongWith(new InstantCommand(Robot.getShooter()::stop))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::deactivateUp))
        );

        operator.getAButton().whenActive(
                new InstantCommand(Robot.getPneumaticsSystem()::dropIntake)
                        .alongWith(new InstantCommand(Robot.getIntake()::forward))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::activateFloorBackward))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::activateUpBackward))
        ).whenInactive(
                new InstantCommand(Robot.getPneumaticsSystem()::pickupIntake)
                        .alongWith(new InstantCommand(Robot.getIntake()::disable))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::deactivateUp))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
        );

        operator.getRBButton().toggleWhenPressed(
                new InstantCommand(
                        Robot.getPneumaticsSystem()::dropIntake)
                        .alongWith(new InstantCommand(Robot.getIntake()::backward))
                        .alongWith(new InstantCommand(Robot.getIntakeIndexer()::activateFloorForward))
        );

        operator.getLBButton().toggleWhenPressed(
                new InstantCommand(
                        Robot.getPneumaticsSystem()::pickupIntake)
                        .andThen(new InstantCommand(Robot.getIntake()::disable))
                        //.andThen(new WaitCommand(1.25))
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
        );

        // Shooter Toggle
        operator.getXButton().toggleWhenPressed(
                // Deactivate intake motor in case
                // Rev up to full speed
                new InstantCommand(Robot.getShooter()::lowGoal)
                        .andThen((new InstantCommand(Robot.getIntake()::disable)))
                        // Pickup intake just in case
                        .andThen(new InstantCommand(Robot.getPneumaticsSystem()::pickupIntake))
                        // Deactivate floor in case
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
                        // pause for 1 second
                        .andThen(new WaitCommand(1))
                        // Turn on up indexer (shoot 1st ball)
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateUpForward))
                        //.andThen(new Wait
                        //Command(0.25))
                        // Turn on floor indexer (shoot 2nd ball)
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateFloorForward))
                        .andThen(new WaitCommand(1))
                        // Turn Off all motors
                        .andThen(new ParallelCommandGroup(
                                new InstantCommand(Robot.getShooter()::stop),
                                new InstantCommand(Robot.getIntakeIndexer()::deactivateUp),
                                new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor)))
        );

        operator.getBButton().whenActive(
                new InstantCommand(Robot.getIntakeIndexer()::activateFloorForward)
        ).whenInactive(
                new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor)
        );

        new DPadButton(operator, DPadButton.Direction.UP).whenActive(new InstantCommand(Robot.getMonkeyBars()::up))
                .whenInactive(new InstantCommand(Robot.getMonkeyBars()::stop));

        new DPadButton(operator, DPadButton.Direction.DOWN).whenActive(new InstantCommand(Robot.getMonkeyBars()::down))
                .whenInactive(new InstantCommand(Robot.getMonkeyBars()::stop));

        new DPadButton(operator, DPadButton.Direction.LEFT).toggleWhenPressed(
                new InstantCommand(Robot.getShooter()::highGoal)
                        .andThen((new InstantCommand(Robot.getIntake()::disable)))
                        // Pickup intake just in case
                        .andThen(new InstantCommand(Robot.getPneumaticsSystem()::pickupIntake))
                        // Deactivate floor in case
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
                        // pause for 1 second
                        .andThen(new WaitCommand(1))
                        // Turn on up indexer (shoot 1st ball)
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateUpForward))
                        //.andThen(new Wait
                        //Command(0.25))
                        // Turn on floor indexer (shoot 2nd ball)
                        .andThen(new InstantCommand(Robot.getIntakeIndexer()::activateFloorForward))
                        .andThen(new WaitCommand(1))
                        // Turn Off all motors
                        .andThen(new ParallelCommandGroup(
                                new InstantCommand(Robot.getShooter()::stop),
                                new InstantCommand(Robot.getIntakeIndexer()::deactivateUp),
                                new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor)))
        );
    }

    public static Controller getDriver() {
        return driver;
    }

    public static Controller getOperator() {
        return operator;
    }
}
