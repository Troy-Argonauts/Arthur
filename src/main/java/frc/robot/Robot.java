// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.auton.commands.DT_MoveToSetpoint;
import frc.robot.auton.routines.ShootAndMoveLow;
import frc.robot.subsystems.*;
import frc.robot.vision.Limelight;

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
    private static Climber climber;
    private static PneumaticsSystem pneumaticsSystem;
    private static Indexer indexer;
    private static Limelight limeLight;

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
        //climber = new Climber();
        pneumaticsSystem = new PneumaticsSystem();
        indexer = new Indexer();

        limeLight = Limelight.getInstance();
        limeLight.setCameraMode(Limelight.CameraMode.DRIVER);
        limeLight.setLedMode(Limelight.LightMode.OFF);

        robotContainer = new RobotContainer();

        driveTrain.zeroEncoders();
        SmartDashboard.putData("Autonomous modes", chooser);
        chooser.setDefaultOption("Shoot and Move Low", new ShootAndMoveLow().withTimeout(15));
        chooser.addOption("Move off Tarmac", new DT_MoveToSetpoint(-40).withTimeout(15));
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        new InstantCommand(() -> Robot.getIntake().setState(Intake.IntakeState.STOPPED), Robot.getIntake())
                .alongWith(new InstantCommand(() -> Robot.getShooter().setState(Shooter.ShooterState.STOPPED), Robot.getShooter()))
                .alongWith(new InstantCommand(() -> Robot.getIndexer().setState(Indexer.IndexerState.STOPPED), Robot.getIndexer()));

    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();

        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

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

    public static Climber getClimber() {
        if (climber == null) climber = new Climber();
        return climber;
    }

    public static PneumaticsSystem getPneumaticsSystem() {
        if (pneumaticsSystem == null) pneumaticsSystem = new PneumaticsSystem();
        return pneumaticsSystem;
    }

    public static Indexer getIndexer() {
        if (indexer == null) indexer = new Indexer();
        return indexer;
    }
}
