package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticsSystem extends SubsystemBase {
    
    Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_SHIFTER_1, Constants.INTAKE_SHIFTER_2);

    public PneumaticsSystem() {
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



    public void shiftIntake() {
        if (DoubleSolenoid.Value.kOff == DoubleSolenoid.Value.kForward) {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        } else {
            solenoid.set(DoubleSolenoid.Value.kForward);
        }
    }

}
