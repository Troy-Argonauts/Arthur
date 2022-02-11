package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final CANSparkMax intakeMotor = new CANSparkMax(Constants.INTAKE, CANSparkMax.MotorType.kBrushless);

    public void activate(double speed) {
        intakeMotor.set(speed);
    }
}
