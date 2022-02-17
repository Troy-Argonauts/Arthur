package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Intake extends SubsystemBase {

    private final CANSparkMax intakeMotor;
    private boolean forward;

    public Intake() {
        intakeMotor = new CANSparkMax(Constants.I_INTAKE, CANSparkMax.MotorType.kBrushless);
    }

    @Override
    public void periodic() {
        if (forward) {
            intakeMotor.set(0.5);
            forward = true;
        } else {
            intakeMotor.set(-0.5);
            forward = false;
        }
    }

    public void toggle() {
        forward = !forward;
    }

    public void disable() {
        intakeMotor.set(0);
    }
}
