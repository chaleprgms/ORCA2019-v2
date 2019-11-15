/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.main.*;


/**
 * Add your docs here.
 */
public class Winch extends Subsystem implements SubsystemInterface {
  VictorSPX winch = new VictorSPX(RobotMap.WINCH);



  @Override
  public void initDefaultCommand() {

  }


  public void periodic(){

    publishData();

  }

  public void publishData(){

    double winchTemp = (winch.getTemperature() * (9/5)) + 32;

    SmartDashboard.putNumber("Winch Temp: ", winchTemp);

  }


  public void winch(){
    winch.set(ControlMode.PercentOutput, .3);
  }

  public void disable(){
    winch.set(ControlMode.PercentOutput, 0);
  }

  public void undoWinch(){
    winch.set(ControlMode.PercentOutput, -.5);
  }
}
