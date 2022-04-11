package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticsSystem extends SubsystemBase {
    
    private final Compressor compressor;
    private final DoubleSolenoid intakeSolenoid, climberSolenoid;
    private DoubleSolenoid.Value intakeCurrentState,climberCurrentState;

    public PneumaticsSystem() { 
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);

        intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Pneumatics.INTAKE_SOLENOID_1, Constants.Pneumatics.INTAKE_SOLENOID_2);
        intakeCurrentState = DoubleSolenoid.Value.kForward;

        climberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Pneumatics.CLIMBER_SOLENOID_1, Constants.Pneumatics.CLIMBER_SOLENOID_2);
        climberCurrentState = DoubleSolenoid.Value.kReverse;

        updateIntakeState();
        updateClimberState();
    }

    public void retractClimber() {
        if (climberCurrentState == DoubleSolenoid.Value.kForward) {
            climberCurrentState = DoubleSolenoid.Value.kReverse;
            updateClimberState();
        }
    }

    public void extendClimber() {
        if (climberCurrentState == DoubleSolenoid.Value.kReverse) {
            climberCurrentState = DoubleSolenoid.Value.kForward;
            updateClimberState();
        }
    }


    public void pickupIntake() {
        if (intakeCurrentState == DoubleSolenoid.Value.kReverse) {
            intakeCurrentState = DoubleSolenoid.Value.kForward;
            updateIntakeState();
        }
    }

    public void dropIntake() {
        if (intakeCurrentState == DoubleSolenoid.Value.kForward) {
            intakeCurrentState = DoubleSolenoid.Value.kReverse;
            updateIntakeState();
        }
    }

    public void updateIntakeState() {
        intakeSolenoid.set(intakeCurrentState);
    }

    public void updateClimberState() {
        climberSolenoid.set(climberCurrentState);
    }
}
