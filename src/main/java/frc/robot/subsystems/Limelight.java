/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {

  private final double STEER_K = 0.03;
  private final double DRIVE_K = 0.26;
  private final double MAX_DRIVE = 0.6;
  private final double DESIRED_TARGET_AREA = 13.0;



  private double tv, tx, ty, ta;

  public boolean m_LimelightHasValidTarget;


  
  public void periodic(){
    
    m_LimelightHasValidTarget = findTarget();

    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); 
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  
  }

  public void initDefaultCommand(){
    // IM FORCED TO PUT THIS HERE FOR NOW

    //TODO: Find a way around this.
    
  }


  public boolean findTarget(){
    boolean foundTarget = false;

    if(tv > 1){

      foundTarget = true;
      return foundTarget;

    }else{

      return foundTarget;
    }

    
  }

  public double getDrive(){

    double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;
    
    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE){ 
     
      drive_cmd = MAX_DRIVE;

    }

    return drive_cmd;

  }


  public double getSteer(){

    double steer_cmd = tx * STEER_K;
    
    return steer_cmd;

  }

}
