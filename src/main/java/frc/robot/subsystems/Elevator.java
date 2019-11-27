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

import com.ctre.phoenix.motorcontrol.*;
import frc.robot.main.RobotMap;
import frc.robot.pid.*;




/**
 * ORCA Elevator, MotionMagic Edition
 */
public class Elevator extends Subsystem implements SubsystemInterface {
  
    private TalonSRX elMotor;  



    public Elevator(){      
      elMotor = new TalonSRX(RobotMap.ELEVATOR_MOTOR);

		  elMotor.config_kP(0, Gains.kP_Elevator, 10);
      elMotor.config_kD(0, Gains.kD_Elevator, 10);

      elMotor.configMotionCruiseVelocity(700, 0);
		  elMotor.configMotionAcceleration(5000, 0);
    }
    
    

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
    public void periodic(){

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
