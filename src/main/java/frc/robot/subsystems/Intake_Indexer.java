package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake_Indexer extends SubsystemBase {

    private final CANSparkMax floorMotor;
    private final CANSparkMax upMotor;
    private boolean active;

    public Intake_Indexer() {
        floorMotor = new CANSparkMax(Constants.I_INDEXER_FLOOR, CANSparkMax.MotorType.kBrushless);
        upMotor = new CANSparkMax(Constants.I_INDEXER_UP, CANSparkMax.MotorType.kBrushless);
    }

    @Override
    public void periodic() {
        if (!active) {
            activate();
            active = true;
        } else {
            floorMotor.set(0);
            upMotor.set(0);
            active = false;
        }
    }

    public void activate() {
        floorMotor.set(0.55);
        upMotor.set(0.55);
    }
    public void toggle() {
        active = !active;
    }
}
