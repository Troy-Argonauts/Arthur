package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake_Indexer extends SubsystemBase {

    private final CANSparkMax floorMotor, upMotor;
    private boolean floorActive, upActive, floorForward, upForward;

    public Intake_Indexer() {
        floorForward = true;
        upForward = true;

        floorMotor = new CANSparkMax(Constants.I_INDEXER_FLOOR, CANSparkMax.MotorType.kBrushless);
        upMotor = new CANSparkMax(Constants.I_INDEXER_UP, CANSparkMax.MotorType.kBrushless);

        floorMotor.setInverted(false);
        upMotor.setInverted(false);

        upMotor.setSmartCurrentLimit(20);
    }

    @Override
    public void periodic() {
        if (floorActive) {
            if (floorForward) {
                activateFloorForward();
            } else {
                activateFloorBackward();
            }
        } else {
            deactivateFloor();
        }

        if (upActive) {
            if (upForward) {
                activateUpForward();
            } else {
                activateUpBackward();
            }
        } else {
            deactivateUp();
        }

        SmartDashboard.putNumber("Indexer Floor Temperature", floorMotor.getMotorTemperature());
        SmartDashboard.putNumber("Indexer Floor Percentage", (floorMotor.get() * 100));
        SmartDashboard.putBoolean("Indexer Floor Active", floorActive);
        SmartDashboard.putNumber("Indexer Up Percentage", (upMotor.get() * 100));
        SmartDashboard.putBoolean("Indexer Up Active", upActive);
    }

    public void activateFloorForward() {
        floorMotor.set(0.3);
        floorActive = true;
        floorForward = true;
    }

    public void activateUpForward() {
            activateUp();
        } else {
            deactivateUp();
        }
        SmartDashboard.putNumber("Indexer Floor Temperature:  ", floorMotor.getMotorTemperature());
        SmartDashboard.putNumber("Indexer Up Temperature:  ", upMotor.getMotorTemperature());
    }

    public void activateFloor() {
        floorMotor.set(0.55);
    }

    public void activateUp() {
        upMotor.set(0.55);
        upActive = true;
        upForward = true;
    }

    public void activateFloorBackward() {
        floorMotor.set(-0.3);
        floorActive = true;
        floorForward = false;
    }

    public void activateUpBackward() {
        upMotor.set(-0.55);
        upActive = true;
        upForward = false;
    }

    public void deactivateFloor() {
        floorMotor.set(0);
        floorActive = false;
        floorForward = true;
    }

    public void deactivateUp() {
        upMotor.set(0);
        upActive = false;
        upForward = true;
    }
  
    public void toggleFloor() {
        floorActive = !floorActive;
    }

    public void toggleUp() {upActive = !upActive;}

    public void toggleAll() {
        toggleFloor();
        toggleUp();
    }
    public void toggleUp() {upActive = !upActive;}
}
