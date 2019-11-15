/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SetPIDWrist;
import frc.robot.main.*;

/**
 *
 */
public class PIDWrist extends PIDSubsystem implements SubsystemInterface {

  
  private TalonSRX m1;
  private double encoderVal;

  
	
    // Initialize your subsystem here
    public PIDWrist() {
      
    	super("PIDWrist", 0.00001, 0.0, 0.0);         // Look to PIDElevator.java for tuning tips
    	setAbsoluteTolerance(50);                     // Tolerance is the threshold at which the PID will strive for
    	getPIDController().setContinuous(false);
    	
      
      m1 = new TalonSRX(RobotMap.TALON_WRIST);
      m1.setSelectedSensorPosition(0);
        
    }

    @Override
    public void periodic(){

        publishData();

        checkTemp();
       
    }

    public void publishData(){
        
        // keeps encoder value constantly pushed to dashboard
        
        encoderVal = m1.getSelectedSensorPosition();
        SmartDashboard.putNumber("Wrist Encoder: ", encoderVal);

    }

    public void disable(){


        m1.set(ControlMode.PercentOutput, 0);


    }

    public void checkTemp(){

        double motorTemp = convF(m1.getTemperature());

        SmartDashboard.putNumber("Elevator Motor Temp: ", motorTemp);
        
        if(motorTemp > 150){

            m1.set(ControlMode.PercentOutput, 0);

        }
    }
    
    public void initDefaultCommand() {

        // Initiates default command

        setDefaultCommand(new SetPIDWrist());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

        SmartDashboard.putNumber("Wrist Encoder: ", encoderVal);
    	  return encoderVal;
    }
    
    protected void usePIDOutput(double output) {
    
        // Handles output usage / pushes info to dashboard

        SmartDashboard.putNumber("Wrist PID Output", output);
        m1.set(ControlMode.PercentOutput, output);
        SmartDashboard.putNumber("Wrist Encoder: ", encoderVal);
    }
    
    public boolean isUp(double setpoint){

        // Checks if wrist is at max val to avoid damage

    	return (encoderVal >= setpoint) ? true:false;
    	
    }

    public double getEncoder(){

      // Gets current encoder value

      return m1.getSelectedSensorPosition();

    }

    public void manualControl(double joystick){
        
        // Sets our wrist manual control value based of operator input

        m1.set(ControlMode.PercentOutput, joystick / 4);
     
    
    }

    public void emergencyUp(){

        // Safety inplace to reduce damage to the manipulator in case of accidental mishandling


        m1.set(ControlMode.PercentOutput, .2);
    }

    public double convF(double tempC){

        return (tempC * (9/5)) + 32;
      }

}
