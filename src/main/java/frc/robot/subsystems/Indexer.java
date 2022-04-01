package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {

    private final CANSparkMax floorMotor, upMotor;
    private boolean floorActive, upActive;

    public Indexer() {
        floorMotor = new CANSparkMax(Constants.Indexer.I_INDEXER_FLOOR, CANSparkMax.MotorType.kBrushless);
        upMotor = new CANSparkMax(Constants.Indexer.I_INDEXER_UP, CANSparkMax.MotorType.kBrushless);

        floorMotor.setInverted(false);
        upMotor.setInverted(false);

        upMotor.setSmartCurrentLimit(20);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Indexer Floor Temperature", floorMotor.getMotorTemperature());
        SmartDashboard.putNumber("Indexer Floor Percentage", (floorMotor.get() * 100));
        SmartDashboard.putBoolean("Indexer Floor Active", floorActive);
        SmartDashboard.putNumber("Indexer Up Percentage", (upMotor.get() * 100));
        SmartDashboard.putBoolean("Indexer Up Active", upActive);
    }

    public void activateFloorForward() {
        floorMotor.set(0.3);
        floorActive = true;
    }

    public void activateUpForward() {
        upMotor.set(0.7);
        upActive = true;
    }

    public void activateFloorBackward() {
        floorMotor.set(-0.3);
        floorActive = true;
    }

    public void activateUpBackward() {
        upMotor.set(-0.7);
        upActive = true;
    }

    public void deactivateFloor() {
        floorMotor.set(0);
        floorActive = false;
    }

    public void deactivateUp() {
        upMotor.set(0);
        upActive = false;
    }
}
