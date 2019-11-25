/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ManualElevator;

import frc.robot.main.*;


public class PIDElevator extends PIDSubsystem implements SubsystemInterface{

  private TalonSRX m1;
  private double encoderVal;
 

    
    public PIDElevator(){
      
       super("PIDElevator", 0.0004, 0.0, 0);    // Tune more narrow then via adding / removing zeros increase or decrease by singular increments
       setAbsoluteTolerance(45);                // Sets absolute tolerance range (Elevator will land withing 45 encoder value of its target)
       getPIDController().setContinuous(false);
    	
       m1 = new TalonSRX(RobotMap.ELEVATOR_MOTOR);
       m1.setSelectedSensorPosition(0);

    }

    @Override
    public void periodic(){

        checkTemp();

        publishData();


    }


    public void checkTemp(){

        double motorTemp = convF(m1.getTemperature());

        SmartDashboard.putNumber("Elevator Motor Temp: ", motorTemp);

        if(motorTemp > 150){

            m1.set(ControlMode.PercentOutput, 0);

        }
    }



 
    public void publishData(){

            encoderVal = m1.getSelectedSensorPosition();
            SmartDashboard.putNumber("Encoder: ", encoderVal);
    
    }
    
    public void initDefaultCommand() {
    
        // Sets default command to use manual operator control as opposed to PID
    
        setDefaultCommand(new ManualElevator());
    }
    
    protected double returnPIDInput() {
        
        // Pushes our encoder value to the dashboard when called.

        SmartDashboard.putNumber("Encoder: ", encoderVal);
    	return encoderVal;
    }
    
    protected void usePIDOutput(double output) {
        
        // Pushes more info to smartdashboard

        SmartDashboard.putNumber("PID Output", output);
        m1.set(ControlMode.PercentOutput, output);
        
        SmartDashboard.putNumber("Encoder: ", encoderVal);
    }

    public void manualControl(double joystick){

        // used to set elevator motor to operator control

        m1.set(ControlMode.PercentOutput, joystick);
        
    
    }

    public void elevatorKill(double encoder){

        // logic to save us from burning up motors by setting elevator output to zero if encoder is at 0

        if(encoder == 0){
            
            m1.set(ControlMode.PercentOutput, 0);
            
        }

    }

    public double getEncoder(){

        // Returns current value of the encoder

        return encoderVal;

    }

    public void resetEncoder(){

        // Automatically sets encoder to 0 when called

        m1.setSelectedSensorPosition(0);

    }

    public double convF(double tempC){

        return (tempC * (9/5)) + 32;
      }

}
