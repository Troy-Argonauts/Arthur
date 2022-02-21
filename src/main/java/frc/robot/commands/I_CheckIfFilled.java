package frc.robot.commands;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;

public class I_CheckIfFilled{

    private I2C.Port SensorPort;
    private ColorSensorV3 m_colorSensor = null;

    public I_CheckIfFilled(int Location){

        SensorPort = Constants.INDEXER_SENSORS[Location];
        m_colorSensor = new ColorSensorV3(SensorPort);
    }



    public void DisplayData(){
        Color detectedColor = m_colorSensor.getColor();
        double IR = m_colorSensor.getIR();

        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("IR", IR);

    }

    public boolean IsFilled (){
        
        Color detectedColor = m_colorSensor.getColor();

        return false;

    }





}

