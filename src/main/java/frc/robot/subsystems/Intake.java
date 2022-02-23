package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final CANSparkMax intakeMotor;
    private boolean forward = true;
    private boolean stopped = true;
    

    public Intake() {
        intakeMotor = new CANSparkMax(Constants.I_INTAKE, CANSparkMax.MotorType.kBrushless);

        intakeMotor.setInverted(true);

        intakeMotor.setIdleMode(IdleMode.kCoast);
    }

    @Override
    public void periodic() {
        if (!stopped) {
            if (forward) {
                forward();
                forward = true;
            } else {
                backward();
                forward = false;
            }
        } else {
            disable();
        }
    }

    public void toggleDirection() {
        forward = !forward;
    }

    public void togglePower() {
        stopped = !stopped;
        forward = true;
    }

    public void forward() {
        intakeMotor.set(0.8);
    }

    public void backward() {
        intakeMotor.set(-0.8);
    }

    public void disable() {
        intakeMotor.set(0);
        stopped = true;
    }
}
