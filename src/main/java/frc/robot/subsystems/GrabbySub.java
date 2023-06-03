package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class GrabbySub  extends SubsystemBase {
  private final DoubleSolenoid grabber;
  
  private final NetworkTable grabbyNetworkTable = NetworkTableInstance.getDefault().getTable(getName());
  private final NetworkTableEntry grabbyExtended = grabbyNetworkTable.getEntry("extended");
  
  private final NetworkTableEntry getGrabby = grabbyNetworkTable.getEntry("getGrabby");
  

  public GrabbySub() {
    grabber = new DoubleSolenoid(Constants.PNEUMATICS_MODULE_TYPE, Constants.FORWARD_CHANNEL, Constants.REVERSE_CHANNEL);
    grabbyExtended.setBoolean(getGrab());
    
  }

  public void setGrab(boolean wantsGrab) {
    grabber.set(wantsGrab ? Constants.INTAKE_EXTENDED_VALUE : Constants.INTAKE_RETRACTED_VALUE);
    grabbyExtended.setBoolean(wantsGrab);
  }

  public boolean getGrab() {
    return grabber.get() == Constants.INTAKE_EXTENDED_VALUE;
  }

  public void toggleGrab() {
    setGrab(!getGrab());
  }
} 
 
