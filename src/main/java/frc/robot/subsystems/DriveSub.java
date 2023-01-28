package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;

//Imports go here :) 

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class DriveSub implements Subsystem {
    //Public finals :)
    private final WPI_VictorSPX frontLeft; 
    private final WPI_VictorSPX frontRight;
    private final WPI_VictorSPX backLeft;
    private final WPI_VictorSPX backRight;
    private final MotorControllerGroup left;
    private final MotorControllerGroup right; 
    public DriveSub() {
        //Construct them bad bois

        //Motors here, name them something good like frontLeft, ect ect. 
        frontLeft = new WPI_VictorSPX(Constants.DRIVETRAIN_LEFT_ID1); //0
        frontRight = new WPI_VictorSPX(Constants.DRIVETRAIN_RIGHT_ID1);//1
        backLeft = new WPI_VictorSPX(Constants.DRIVETRAIN_LEFT_ID2);//2
        backRight = new WPI_VictorSPX(Constants.DRIVETRAIN_RIGHT_ID2);//3

        left = new MotorControllerGroup(frontLeft, backLeft);
        
        right = new MotorControllerGroup(frontRight, backRight);
        right.setInverted(true);
    }
    public void arcadeDrive(double move, double turn) {
        left.set(move - turn);
        right.set(move + turn); 
        
    }
}