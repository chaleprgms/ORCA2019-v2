/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ManualElevator;


public class PIDElevator extends PIDSubsystem {

  private DigitalInput limitSwitch;
  private TalonSRX m1;
  private double encoderVal;


	
    
    public PIDElevator(){
      
       super("PIDElevator", 0.0004, 0.0, 0);    // Tune more narrow then via adding / removing zeros increase or decrease by singular increments
       setAbsoluteTolerance(45);                // Sets absolute tolerance range (Elevator will land withing 45 encoder value of its target)
       getPIDController().setContinuous(false);
    	
      
       // Handles bottom limit switch resetting values to zero after usage of PID / elevator bottoming out
        
       limitSwitch = new DigitalInput(0);
       m1 = new TalonSRX(RobotMap.ELEVATOR_MOTOR);
       m1.setSelectedSensorPosition(0);

    }

    @Override
    public void periodic(){
        
        // Periodic is used to keep constant eye on the state of our encoders / whether or not our limit switch is pressed.

        if(!limitSwitch.get()){

            m1.setSelectedSensorPosition(0);
            encoderVal = m1.getSelectedSensorPosition();
            SmartDashboard.putNumber("Encoder: ", encoderVal);
        }else{
            encoderVal = m1.getSelectedSensorPosition();
            SmartDashboard.putNumber("Encoder: ", encoderVal);
        }
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
    
    public boolean getLimit(){

        // returns the current state of the limit switch

    	return limitSwitch.get();	
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

}
