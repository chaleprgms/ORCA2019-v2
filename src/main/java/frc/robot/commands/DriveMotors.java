/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.main.*;


public class DriveMotors extends Command {
  public DriveMotors() {
    requires(Robot.m_drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  
    // initializes all Controller axes values
  
    double left = Robot.m_oi.controller.getRawAxis(1);
    double right = Robot.m_oi.controller.getRawAxis(5);
    double left_trigger = Robot.m_oi.controller.getRawAxis(2);
    double right_trigger = Robot.m_oi.controller.getRawAxis(3);

    // Logic to see if left or right trigger is pushed to initiate T-Strafe or normal Tank Drive
  
    if(left_trigger > 0){
      Robot.m_drive.strafeLeft();
    }else if(right_trigger > 0){
      Robot.m_drive.strafeRight();
    }else{
      if(Math.abs(left)<RobotMap.DEADZONE){
          left = 0;
        }else if(Math.abs(right)<RobotMap.DEADZONE){
          right = 0;
      }

      Robot.m_drive.drive();
    }


    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
