// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.libs.util.Controller;
import frc.robot.commands.*;

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

  private final S_ShooterToggle s_shooterToggle;
  private final I_IntakeToggle i_intakeToggle;
  private final PS_ToggleCompressor ps_toggleCompressor;
  private final PS_PickupIntake ps_pickupIntake;
  private final PS_DropIntake ps_dropIntake;
  private final MB_Down mb_down;
  private final MB_Up mb_up;
  private final MB_Stop mb_stop;
  private final I_IndexerToggle i_indexerToggle;
  private final I_IntakeStop i_intakeStop;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driver = new Controller(Constants.DRIVER_PORT);
    operator = new Controller(Constants.OPERATOR_PORT);

    s_shooterToggle = new S_ShooterToggle();
    ps_toggleCompressor = new PS_ToggleCompressor();
    ps_pickupIntake = new PS_PickupIntake();
    ps_dropIntake = new PS_DropIntake();
    i_intakeToggle = new I_IntakeToggle();
    mb_down = new MB_Down();
    mb_up = new MB_Up();
    mb_stop = new MB_Stop();
    i_indexerToggle = new I_IndexerToggle();
    i_intakeStop = new I_IntakeStop();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Trigger rightTrigger = new Trigger( () -> operator.getRightTrigger() > 0);
    Trigger leftTrigger = new Trigger(() -> operator.getLeftTrigger() > 0 );

    operator.getXButton().toggleWhenPressed(s_shooterToggle);
    operator.getYButton().toggleWhenActive(ps_toggleCompressor);
    operator.getAButton().whenPressed(i_intakeStop);
    operator.getRBButton().toggleWhenActive(ps_dropIntake);
    operator.getLBButton().toggleWhenActive(ps_pickupIntake);
    operator.getAButton().toggleWhenActive(i_intakeToggle);
    operator.getBButton().toggleWhenPressed(i_indexerToggle);

    rightTrigger.whenActive(mb_up).whenInactive(mb_stop);
    leftTrigger.whenActive(mb_down).whenInactive(mb_stop);
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
