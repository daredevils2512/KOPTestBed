package frc.robot.commands.AutoCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PigeonSub;

public class TurnToAngle extends CommandBase{

    private final DriveSub m_DriveSub;
    private final PigeonSub m_PigeonSub;
    private double yawTarget;
    private final int m_angleToTurnTO;
    PIDController pid = new PIDController(Constants.AutoK_P, Constants.AutoK_I, Constants.AutoK_D);

    public TurnToAngle(DriveSub driveSub, PigeonSub pigeonSub, Integer angleToTurnTO) {
    m_DriveSub = driveSub;
    m_PigeonSub = pigeonSub; 
    m_angleToTurnTO = angleToTurnTO;
    
   
    addRequirements(m_DriveSub, m_PigeonSub);
    }
     /** Called when the command is initially scheduled. */
  @Override
  public void initialize() {  
  System.out.println("Turning!!");
  yawTarget = m_PigeonSub.getYaw() + m_angleToTurnTO;
  }

  /** Called every time the scheduler runs while the command is scheduled. */
  @Override
  public void execute() {
   
    // double output = Constants.AutoK_P * (yawTarget - m_PigeonSub.getYaw());
    double output = pid.calculate(m_PigeonSub.getYaw(), yawTarget);
    m_DriveSub.arcadeDrive(0, -output);
   
  }

  /** Called once the command ends or is interrupted. */
  @Override
  public void end(boolean interrupted) {
    m_DriveSub.arcadeDrive(0, 0);
    
  }

  /** Returns true when the command should end. */
  @Override
  public boolean isFinished() {
    return false;
  }
}
