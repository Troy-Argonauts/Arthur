package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final CANSparkMax intakeMotor;
    private boolean stopped;
    

    public Intake() {
        stopped = true;

        intakeMotor = new CANSparkMax(Constants.Intake.PORT, CANSparkMax.MotorType.kBrushless);

        intakeMotor.setInverted(false);

        intakeMotor.setIdleMode(IdleMode.kCoast);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Intake Active", !stopped);
    }

    public void disable() {
        intakeMotor.set(0);
        stopped = true;
    }

    public void setState(IntakeState state) {
        switch (state) {
            case OUT:
                intakeMotor.set(0.9);
                stopped = false;
                break;
            case IN:
                intakeMotor.set(-0.9);
                stopped = false;
                break;
            case STOPPED:
                intakeMotor.set(0);
                stopped = true;
                break;
        }
    }

    public enum IntakeState {
        IN, OUT, STOPPED
    }
}
