/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.IntakeIdle;
import frc.robot.main.*;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem implements SubsystemInterface {
  
  TalonSRX intake_motor = new TalonSRX(RobotMap.INTAKE_CANID);


  

  @Override
  public void initDefaultCommand() {
    
    setDefaultCommand(new IntakeIdle());

  }

  public void periodic(){
  
    publishData();
  
  }

  public void intakeIn(){
    
    intake_motor.set(ControlMode.PercentOutput, -1);

  }

  public void intakeOut(){
    
      intake_motor.set(ControlMode.PercentOutput, 1);
  
    }

  public void intakeIdle(){
    intake_motor.set(ControlMode.PercentOutput, -.3);
  }

  public void disable(){
    intake_motor.set(ControlMode.PercentOutput, 0);
  }
  
  public void override(){
  
    intake_motor.set(ControlMode.PercentOutput, 1);
  
  }

  public void publishData(){

    int currentIntakeSpeed = intake_motor.getSelectedSensorVelocity();

    SmartDashboard.putNumber("Intake Velocity: ", currentIntakeSpeed);
  }


}
