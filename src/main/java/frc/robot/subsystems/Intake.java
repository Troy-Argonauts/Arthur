package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final CANSparkMax intakeMotor;
    private boolean active;

    public enum IntakeState {
        IN, OUT, STOPPED
    }

    public Intake() {
        intakeMotor = new CANSparkMax(Constants.Intake.PORT, CANSparkMax.MotorType.kBrushless);
        intakeMotor.setInverted(false);
        intakeMotor.setIdleMode(IdleMode.kCoast);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Intake Active", active);
    }

    public void setState(IntakeState state) {
        switch (state) {
            case OUT:
                intakeMotor.set(Constants.Intake.SPEED);
                active = true;
                break;
            case IN:
                intakeMotor.set(-Constants.Intake.SPEED);
                active = true;
                break;
            case STOPPED:
                intakeMotor.set(0);
                active = false;
                break;
        }
    }
}
