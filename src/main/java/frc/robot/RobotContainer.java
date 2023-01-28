// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.cscore.UsbCamera;

import frc.robot.Constants;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.AutoCommands.AutoCommands;
import frc.robot.commands.AutoCommands.TurnToAngle;
import frc.robot.subsystems.CompressorSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PigeonSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final PigeonSub m_PigeonSub = new PigeonSub();
  private final DriveSub m_driveSub = new DriveSub();
  private final CompressorSub m_CompressorSub = new CompressorSub();

  private final Command m_turn90Left;
  private final Command m_turn90Right;
  private final Command m_turn180;

  private final Command m_fullAuto;
  

  private final CommandXboxController m_driverController =
  new CommandXboxController(0);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
   m_CompressorSub.setClosedLoopControl(Constants.COMPRESSOR_CLOSED_LOOP_CONTROL_ENABLED);
   //m_CompressorSub.enableDigital();
   m_turn90Left = new TurnToAngle(m_driveSub, m_PigeonSub, 90);
   m_turn90Right = new TurnToAngle(m_driveSub, m_PigeonSub, -90);
   m_turn180 = new TurnToAngle(m_driveSub, m_PigeonSub, 180);
   m_fullAuto = AutoCommands.fullAuto(m_driveSub, 1, m_PigeonSub, 90);
    // Configure the trigger bindings
    configureBindings();
    //start camera server
    UsbCamera camera = CameraServer.startAutomaticCapture();
    camera.setResolution(640,480);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    m_driveSub.setDefaultCommand(m_driveSub.run(() -> m_driveSub.arcadeDrive(m_driverController.getLeftY(), m_driverController.getRightX())));
    m_driverController.x().whileTrue(m_turn90Left);
    m_driverController.b().whileTrue(m_turn90Right);
    m_driverController.y().whileTrue(m_turn180);
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_fullAuto;
  }
}
