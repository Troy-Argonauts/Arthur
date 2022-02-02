package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants;

public class Shooter {
    
    private TalonFX right = new TalonFX(Constants.S_RIGHT);
    private TalonFX left = new TalonFX(Constants.S_LEFT);

    public Shooter() {
        
    }
}
