package frc.robot.commands.AutoCommands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class AutoDrive extends CommandBase {
  private final DriveSub m_driveSub;

  private final double speed;

  public AutoDrive(DriveSub driveSub, double speed) {
    m_driveSub = driveSub;
    this.speed = speed;
   
    addRequirements(m_driveSub);
  }

  @Override
  public void initialize() {
  
  }

  @Override
  public void execute() {
    m_driveSub.arcadeDrive(-speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    m_driveSub.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
 
  }
}
