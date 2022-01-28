package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.Constants;

public class Intake {

    CANSparkMax intakeMotor = new CANSparkMax(Constants.INTAKE_INTAKE, CANSparkMaxLowLevel.MotorType.kBrushless);
    public Intake() {

    }
}
