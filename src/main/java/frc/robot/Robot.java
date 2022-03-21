// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.auton.commands.DT_MoveToSetpoint;
import frc.robot.auton.routines.Shoot2;
import frc.robot.auton.routines.ShootAndMoveHigh;
import frc.robot.auton.routines.ShootAndMoveLow;
import frc.robot.auton.routines.ShootAndPush;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command autonomousCommand;

    public static RobotContainer robotContainer;
    private static DriveTrain driveTrain;
    private static Intake intake;
    private static Shooter shooter;
    private static MonkeyBars monkeyBars;
    private static PneumaticsSystem pneumaticsSystem;
    private static Intake_Indexer intake_indexer;
    public boolean robotOn;

    private final SendableChooser<Command> chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Initialize Subsystems
        driveTrain = new DriveTrain();
        intake = new Intake();
        shooter = new Shooter();
        monkeyBars = new MonkeyBars();
        pneumaticsSystem = new PneumaticsSystem();
        intake_indexer = new Intake_Indexer();
        // Instantiate our RobotContainer. This will perform all our button bindings
        robotContainer = new RobotContainer();

        driveTrain.zeroEncoders();
        SmartDashboard.putBoolean("Robot On", robotOn);

        // This will place our autonomous chooser on the dashboard.
        SmartDashboard.putData("Autonomous modes", chooser);
        chooser.setDefaultOption("Shoot and Move Low", new ShootAndMoveLow().withTimeout(15));
        chooser.addOption("Shoot and Move High", new ShootAndMoveHigh().withTimeout(15));
        chooser.addOption("Nothing", new WaitCommand(15));
        chooser.addOption("Move off Tarmac", new DT_MoveToSetpoint(-20));
        chooser.addOption("Shoot and Push", new ShootAndPush().withTimeout(15));
        chooser.addOption("2 Ball", new Shoot2().withTimeout(15));
//		chooser.addOption("3 Ball", new Shoot3().withTimeout(15));
//		chooser.addOption("4 Ball", new Shoot4().withTimeout(15));
//	    chooser.addOption("Remove Preloaded Ball", new Extake().withTimeout(15));
//		chooser.addOption("Intake Red Ball", new IntakeRedBall().withTimeout(15));
//		chooser.addOption("Intake Red Balls", new Intake2RedBalls().withTimeout(15));
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();

        SmartDashboard.putNumber("Driver Right Joystick X Value", RobotContainer.getDriver().getRightJoystickX());
        SmartDashboard.putNumber("Driver Left Joystick Y Value", RobotContainer.getDriver().getLeftJoystickY());

        robotOn = true;
    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
        new InstantCommand(Robot.getIntake()::disable)
                .alongWith(new InstantCommand(Robot.getShooter()::stop))
                .alongWith(new InstantCommand(Robot.getIntakeIndexer()::deactivateFloor))
                .alongWith(new InstantCommand(Robot.getIntakeIndexer()::deactivateUp));
    }

    @Override
    public void disabledPeriodic() {
        robotOn = false;
    }

    /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();

        // schedule the autonomous command (example)
        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}

    public static DriveTrain getDriveTrain() {
        if (driveTrain == null) driveTrain = new DriveTrain();
        return driveTrain;
    }

    public static Intake getIntake() {
        if (intake == null) intake = new Intake();
        return intake;
    }

    public static Shooter getShooter() {
        if (shooter == null) shooter = new Shooter();
        return shooter;
    }

    public static MonkeyBars getMonkeyBars() {
        if (monkeyBars == null) monkeyBars = new MonkeyBars();
        return monkeyBars;
    }

    public static PneumaticsSystem getPneumaticsSystem() {
        if (pneumaticsSystem == null) pneumaticsSystem = new PneumaticsSystem();
        return pneumaticsSystem;
    }

    public static Intake_Indexer getIntakeIndexer() {
        if (intake_indexer == null) intake_indexer = new Intake_Indexer();
        return intake_indexer;
    }
}
