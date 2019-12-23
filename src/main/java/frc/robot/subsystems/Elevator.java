/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ManualElevator;

import frc.robot.RobotMap;
import frc.robot.pid.*;




/**
 * ORCA Elevator, MotionMagic Edition
 */
public class Elevator extends Subsystem implements SubsystemInterface {
  
    private TalonSRX elMotor;  
    private double encoder;


    public Elevator(){      

      
      elMotor = new TalonSRX(RobotMap.ELEVATOR_MOTOR);

      
      // Initializes PID Gains for the elevator subsystem
      // Ignores kF (Feed Forward), dont need it for this case
      
      elMotor.config_kP(0, Gains.kP_Elevator, 10);
      elMotor.config_kI(0, Gains.kI_Elevator, 10);
      elMotor.config_kD(0, Gains.kD_Elevator, 10);



      /*  Sets our motor Cruise / Acceleration velocity
       *  Max # of encoder units allowable for Cruise / Acceleration
       *  Stops us from running a motor too quickly to a setpoint
       */

      elMotor.configMotionCruiseVelocity(700, 0);
      elMotor.configMotionAcceleration(5000, 0);


      // Sets current limit so we dont accidentally cause a fire

      elMotor.configPeakCurrentLimit(40);
      elMotor.enableCurrentLimit(true);
      

      
    }
    
    

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualElevator());
  }

  @Override
    public void periodic(){

        encoder = elMotor.getSelectedSensorPosition();

        checkTemp();

        publishData();


    }

    public void checkTemp(){

      double motorTemp = convF(elMotor.getTemperature());

      SmartDashboard.putNumber("Elevator Motor Temp: ", motorTemp);

      if(motorTemp > 150){

          elMotor.set(ControlMode.PercentOutput, 0);

      }
  }




  public void publishData(){

          double encoderVal = elMotor.getSelectedSensorPosition();
          SmartDashboard.putNumber("Encoder: ", encoderVal);
  
  }

  public void manualControl(double joystick){

    // used to set elevator motor to operator control

    elMotor.set(ControlMode.PercentOutput, joystick);

  }

  public void elevatorKill(double encoder){

    // logic to save us from burning up motors by setting elevator output to zero if encoder is at 0

    if(encoder == 0){
        
        elMotor.set(ControlMode.PercentOutput, 0);
        
    }

  }   

  public double getEncoder(){

    return encoder;

  }

  public void goTo(double height){

    elMotor.set(ControlMode.MotionMagic, height);

  }

  public void disable(){

    elMotor.set(ControlMode.PercentOutput, 0);

  }

  public double convF(double tempC){

    return (tempC * (9/5)) + 32;
}


}
