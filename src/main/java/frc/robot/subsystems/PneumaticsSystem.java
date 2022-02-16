package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticsSystem extends SubsystemBase {
    
    private final Compressor compressor;
    private final DoubleSolenoid solenoid;
    private DoubleSolenoid.Value currentState;

    public PneumaticsSystem() {
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);

        solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_SHIFTER_1, Constants.INTAKE_SHIFTER_2);
        currentState = DoubleSolenoid.Value.kReverse;
        updateState();

        compressor.enableDigital();
    }

    public void toggleCompressor() {
        if (compressor.enabled()) {
            compressor.disable();
        } else {
            compressor.enableDigital();
        }
    }

    public void stop() {
        compressor.disable();
        solenoid.close();
    }


    public void toggleSolenoids() {
        if (currentState == DoubleSolenoid.Value.kForward) {
            currentState = DoubleSolenoid.Value.kReverse;
        } else {
            currentState = DoubleSolenoid.Value.kForward;
        }
        updateState();
    }

    public void updateState() {
        solenoid.set(currentState);
    }

}
