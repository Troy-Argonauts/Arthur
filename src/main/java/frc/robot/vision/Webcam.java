package frc.robot.vision;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Webcam extends SubsystemBase {

	private UsbCamera camera;

	public Webcam() {
		this.camera = CameraServer.startAutomaticCapture("usb0", 0);
		this.camera.setFPS(30);
	}
}
