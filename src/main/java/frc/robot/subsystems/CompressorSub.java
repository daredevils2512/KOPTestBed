package frc.robot.subsystems;

import com.ctre.phoenix.Logger;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CompressorSub extends SubsystemBase {

    private Compressor compressor = new Compressor(0, Constants.PNEUMATICS_MODULE_TYPE );
    public CompressorSub() {

    }
    
    
    public void periodic() {
        // java.util.logging.Logger.getLogger(getName()).info("pressure" + compressor.getPressure());
        // java.util.logging.Logger.getLogger(getName()).info("curretn " + compressor.getCurrent());
        // java.util.logging.Logger.getLogger(getName()).info("is enables"+compressor.getPressureSwitchValue());
    }

  
  public void setClosedLoopControl(boolean wantsClosedLoopControl) {
    if (wantsClosedLoopControl) {
      compressor.enableDigital();
    } else {
      compressor.disable();
    }
    
  }
    
  public void enableDigital(){
    compressor.enableDigital();
  }

  public boolean getClosedLoopControl() {
    return compressor.isEnabled();
  }

  
  public void toggleCompressor() {
    if (getClosedLoopControl()) {
      compressor.disable(); 
    } else {
      compressor.enableDigital();
    }
  }

}
