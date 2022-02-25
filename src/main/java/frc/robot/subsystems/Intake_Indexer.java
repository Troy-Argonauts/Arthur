package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake_Indexer extends SubsystemBase {

    private final CANSparkMax floorMotor, upMotor;
    private Ultrasonic bottomIndexerSensor;
    private boolean active;

    public Intake_Indexer() {
        floorMotor = new CANSparkMax(Constants.I_INDEXER_FLOOR, CANSparkMax.MotorType.kBrushless);
        upMotor = new CANSparkMax(Constants.I_INDEXER_UP, CANSparkMax.MotorType.kBrushless);
        bottomIndexerSensor = new Ultrasonic(Constants.BOTTOMINDEXERSENSOR_PING, Constants.BOTTOMINDEXERSENSOR_RESPONSE);
    }

    @Override
    public void periodic() {
        if (active) {
            activate();
        } else {
            deactivate();
        }
        // SmartDashboard.putNumber("Indexer Floor Temperature", floorMotor.getMotorTemperature());
        // SmartDashboard.putNumber("Indexer Up Temperature", upMotor.getMotorTemperature());
    }

    public void activate() {
<<<<<<< Updated upstream
        floorMotor.set(0.55);
        upMotor.set(0.55);
=======
        floorMotor.set(0.5);
        upMotor.set(0.4);
>>>>>>> Stashed changes
    }

    public void deactivate() {
        floorMotor.set(0);
        upMotor.set(0);
    }
  
    public void toggle() {
        active = !active;
    }
}
