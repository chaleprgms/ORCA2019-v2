/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
public class SetPIDWrist extends Command {
  private double encoderValue;

  public SetPIDWrist() {
    requires(Robot.m_pidWrist);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }
  @Override
  protected void execute() {
    
    // Needs tweaking, sets PID output based of last encoder value so the wrist can hold position when not in use
    
    if(Robot.m_oi.JSAuxTwo.getRawAxis(1) > .05 || Robot.m_oi.JSAuxTwo.getRawAxis(1) < -.05){    
      Robot.m_pidWrist.disable();
      Robot.m_pidWrist.manualControl(Robot.m_oi.JSAuxTwo.getRawAxis(1));
    }else{
      encoderValue = Robot.m_pidWrist.getEncoder();
      Robot.m_pidWrist.enable();
      Robot.m_pidWrist.setSetpoint(encoderValue);
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
