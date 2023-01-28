package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PigeonSub extends SubsystemBase {
    private final WPI_PigeonIMU pigeon;
    
    private NetworkTable table;
    private NetworkTableEntry rollEnrty;
    private NetworkTableEntry yawEnrty;
    private NetworkTableEntry pichEnrty;

    public PigeonSub() {
        table = NetworkTableInstance.getDefault().getTable("Pigeon");
        rollEnrty = table.getEntry("Roll");
        yawEnrty = table.getEntry("Yaw");
        pichEnrty = table.getEntry("Pitch");

        pigeon = new WPI_PigeonIMU(Constants.PIGEON_PORT);
    }
    public void periodic() {
        rollEnrty.setNumber(pigeon.getRoll());
        yawEnrty.setNumber(pigeon.getYaw());
        pichEnrty.setNumber(pigeon.getPitch());
    }
    public double[] getGyro() {
        double[] d = {getYaw(), getPitch(), getRoll()};
        return d;
    }

    public double getYaw() {
        return pigeon.getYaw();
    }

    public double getPitch() {
        return pigeon.getPitch();
    }

    public double getRoll() {
        return pigeon.getRoll();
    }

}