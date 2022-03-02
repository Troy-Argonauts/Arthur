package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final CANSparkMax intakeMotor;
    private boolean forward, stopped;
    

    public Intake() {
        forward = true;
        stopped = true;

        intakeMotor = new CANSparkMax(Constants.I_INTAKE, CANSparkMax.MotorType.kBrushless);

        intakeMotor.setInverted(true);

        intakeMotor.setIdleMode(IdleMode.kCoast);
    }

    @Override
    public void periodic() {
        if (!stopped) {
            if (forward) {
                forward();
            } else {
                backward();
            }
        } else {
            disable();
        }
        SmartDashboard.putNumber("Intake Temperature", intakeMotor.getMotorTemperature());
        SmartDashboard.putNumber("Intake Percentage", (intakeMotor.get() * 100));
        SmartDashboard.putBoolean("Intake Forward", forward);
        SmartDashboard.putBoolean("Intake Active", !stopped);
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
        stopped = false;
        forward = true;
    }

    public void backward() {
        intakeMotor.set(-0.8);
        stopped = false;
        forward = false;
    }

    public void disable() {
        intakeMotor.set(0);
        stopped = true;
        forward = true;
    }
}
