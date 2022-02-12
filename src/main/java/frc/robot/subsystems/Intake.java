package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Intake extends SubsystemBase {

    private final CANSparkMax intakeMotor = new CANSparkMax(Constants.I_INTAKE, CANSparkMax.MotorType.kBrushless);
    private final CANSparkMax indexerMotor = new CANSparkMax(Constants.I_INDEXER, CANSparkMax.MotorType.kBrushless);

    public void activate(double speed) {
        intakeMotor.set(speed);
        indexerMotor.set(speed);
    }

    public void executeForward() {
        Robot.getIntake().activate(0.5);
    }
    public void executeBackward() {
        Robot.getIntake().activate(-0.5);
    }
}
