package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake_Indexer extends SubsystemBase {

    private final CANSparkMax floorMotor, upMotor;
    private Ultrasonic bottomIndexerSensor;
    private boolean active;

    public Intake_Indexer() {
        active = false;

        floorMotor = new CANSparkMax(Constants.I_INDEXER_FLOOR, CANSparkMax.MotorType.kBrushless);
        upMotor = new CANSparkMax(Constants.I_INDEXER_UP, CANSparkMax.MotorType.kBrushless);
        bottomIndexerSensor = new Ultrasonic(Constants.BOTTOMINDEXERSENSOR_PING, Constants.BOTTOMINDEXERSENSOR_RESPONSE);
    }

    @Override
    public void periodic() {
        if (!active) {
            if (bottomIndexerSensor.getRangeInches() > 1) {
                activate();
                active = true;
            }
        } else {
            deactivate();
            active = false;
        }
    }

    public void activate() {
        floorMotor.set(0.55);
        upMotor.set(0.55);
    }

    public void deactivate() {
        floorMotor.set(0);
        upMotor.set(0);
    }
  
    public void toggle() {
        active = !active;
    }
}
