/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class SetElevatorSetpoint extends Command {

    private double setpoint;
    private double encoderVal;
    private boolean finished;
    

    public SetElevatorSetpoint(double setpoint) {

        // Allows us to set our desired setpoint for PID to land at
    	this.setpoint = setpoint;
        this.finished = false;
        requires(Robot.m_pid);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

       this.encoderVal = Robot.m_pid.getEncoder();

       if(encoderVal == 0){
       
            Robot.m_pid.disable();
            this.finished = true;
       
        }else if(encoderVal > 0){

           Robot.m_pid.goTo(setpoint);
           this.finished = false;
       
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

    	return this.finished;
    
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}