/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {

  private final double STEER_K = 0.03;
  private final double DRIVE_K = 0.26;
  private final double MAX_DRIVE = 0.7;


  private double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
  private double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  private double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  private double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

  public boolean m_LimelightHasValidTarget = false;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


}
