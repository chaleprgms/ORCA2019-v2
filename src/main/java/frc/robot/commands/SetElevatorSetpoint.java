/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.main.*;

public class SetElevatorSetpoint extends Command {

    private double setpoint;
    private boolean finished = false;
	
    public SetElevatorSetpoint(double setpoint) {

        // Allows us to set our desired setpoint for PID to land at

    	this.setpoint = setpoint;
        requires(Robot.m_pid);
      
    }

    // Called just before this Command runs the first time
    protected void initialize() {

        // Enables PID while constantly checking state for encoder to make sure things dont break
        Robot.m_pid.enable();

        if(setpoint == 0){
            
            // Sets our Zero'd setpoint with safety in place for co-driver to avoid damaging intake
            
            Robot.m_pid.setSetpoint(setpoint);
            

            Robot.m_pidWrist.emergencyUp();

        }else{

            //Otherwise sets PID setpoint until command is overridden

            Robot.m_pid.setSetpoint(setpoint);

        }
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        // When command is overridden, push state to dashboard
        
        finished = true;
        SmartDashboard.putBoolean("Elevator Finish:", finished);
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}