// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.libs.util.ArgoController;
import frc.libs.util.DPadButton;
import frc.robot.commands.ShooterHigh;
import frc.robot.commands.ShooterLow;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...

    public static ArgoController driver;
    public static ArgoController operator;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        driver = new ArgoController(Constants.Controller.DRIVER_PORT);
        operator = new ArgoController(Constants.Controller.OPERATOR_PORT);

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

        RunCommand driverCommand = new RunCommand(() ->  {Robot.getDriveTrain().cheesyDrive(driver.getRightJoystickX(), driver.getLeftJoystickY(), 0.7);
        }, Robot.getDriveTrain());

        // Driver Controls
        Robot.getDriveTrain().setDefaultCommand(

                driverCommand
        );

        driver.getBButton().toggleWhenPressed(
            new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.STOPPED), Robot.getIntake())
                .alongWith(new InstantCommand(() -> Robot.getShooter().setState(Shooter.ShooterState.STOPPED), Robot.getShooter()))
                .alongWith(new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED), Robot.getIndexer()))
        );

        driver.getAButton().whenActive(
                new InstantCommand(() -> driverCommand.end(false))
                        .andThen(new InstantCommand(() ->  Robot.getDriveTrain().brakeMode())
                                .alongWith(new RunCommand(() -> Robot.getDriveTrain().brakeMode(), Robot.getDriveTrain()))));  // End the command


        driver.getAButton().whenInactive(
                new InstantCommand(() -> driverCommand.execute()));


        operator.getAButton().whenActive(
            new InstantCommand(Robot.getPneumaticsSystem()::dropIntake)
                .alongWith(new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.OUT), Robot.getIntake()))
                .alongWith(new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.OUT), Robot.getIndexer()))
        ).whenInactive(
            new InstantCommand(Robot.getPneumaticsSystem()::pickupIntake)
                .alongWith(new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.STOPPED), Robot.getIntake()))
                .alongWith(new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED), Robot.getIndexer()))
        );

        operator.getRBButton().toggleWhenPressed(
            new InstantCommand(
                Robot.getPneumaticsSystem()::dropIntake)
                .alongWith(new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.IN), Robot.getIntake()))
                .alongWith(new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.IN), Robot.getIndexer()))
        );

        operator.getLBButton().toggleWhenPressed(
            new InstantCommand(
                Robot.getPneumaticsSystem()::pickupIntake)
                .andThen(new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.STOPPED), Robot.getIntake()))
                .andThen(new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED), Robot.getIndexer()))
        );

        operator.getXButton().toggleWhenPressed(
            new ShooterLow()
        );

        operator.getBButton().whenActive(
            new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.IN), Robot.getIndexer())
        ).whenInactive(
            new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED), Robot.getIndexer())
        );

        new DPadButton(operator, DPadButton.Direction.UP).whenActive(
            new InstantCommand(() -> Robot.getClimber().setState(Climber.ClimberState.UP), Robot.getClimber())
        ).whenInactive(
            new InstantCommand(() -> Robot.getClimber().setState(Climber.ClimberState.STOPPED), Robot.getClimber())
        );

        new DPadButton(operator, DPadButton.Direction.DOWN).whenActive(
            new InstantCommand(() -> Robot.getClimber().setState(Climber.ClimberState.DOWN), Robot.getClimber())
        ).whenInactive(
            new InstantCommand(() -> Robot.getClimber().setState(Climber.ClimberState.STOPPED), Robot.getClimber())
        );

        new DPadButton(operator, DPadButton.Direction.LEFT).toggleWhenPressed(
            new ShooterHigh()
        );
    }

    public ArgoController getDriver() {
        return driver;
    }
}
