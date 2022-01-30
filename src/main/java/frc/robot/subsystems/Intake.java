package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.I_DriveIntake;

public class Intake extends SubsystemBase {

    private CANSparkMax intakeMotor = new CANSparkMax(Constants.INTAKE, CANSparkMax.MotorType.kBrushless);

    public Intake() {
        setDefaultCommand(new I_DriveIntake());
    }

    public void activate(double speed) {
        
    }
}
