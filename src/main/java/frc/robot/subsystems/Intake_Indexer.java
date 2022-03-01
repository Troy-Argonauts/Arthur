package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake_Indexer extends SubsystemBase {

    private final CANSparkMax floorMotor, upMotor;
    private Ultrasonic bottomIndexerSensor;
    private boolean floorActive, upActive;

    public Intake_Indexer() {
        floorMotor = new CANSparkMax(Constants.I_INDEXER_FLOOR, CANSparkMax.MotorType.kBrushless);
        upMotor = new CANSparkMax(Constants.I_INDEXER_UP, CANSparkMax.MotorType.kBrushless);
        bottomIndexerSensor = new Ultrasonic(Constants.BOTTOMINDEXERSENSOR_PING, Constants.BOTTOMINDEXERSENSOR_RESPONSE);
    }

    @Override
    public void periodic() {
        if (floorActive) {
            activateFloor();
        } else {
            deactivateFloor();
        }

        if (upActive) {
            activateUp();
        } else {
            deactivateUp();
        }
        SmartDashboard.putNumber("Indexer Floor Temperature", floorMotor.getMotorTemperature());
        SmartDashboard.putNumber("Indexer Floor Percentage", (floorMotor.get() * 100));
        SmartDashboard.putBoolean("Indexer Floor Active", floorActive);
        SmartDashboard.putNumber("Indexer Up Temperature", upMotor.getMotorTemperature());
        SmartDashboard.putNumber("Indexer Up Percentage", (upMotor.get() * 100));
        SmartDashboard.putBoolean("Indexer Up Active", upActive);
    }

    public void activateFloor() {
        floorMotor.set(0.3);
    }

    public void activateUp() {
        upMotor.set(0.55);
    }

    public void deactivateFloor() {
        floorMotor.set(0);
    }

    public void deactivateUp() {
        upMotor.set(0);
    }
  
    public void toggleFloor() {
        floorActive = !floorActive;
    }
    public void toggleUp() {upActive = !upActive;}
}
