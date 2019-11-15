/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveMotors;
import frc.robot.main.*;



public class NeoDriveBase extends Subsystem implements SubsystemInterface {
  
  CANSparkMax lf_motor, rf_motor, lb_motor, rb_motor;

  AHRS navx;

  double gyroRead, accelX, accelY, accelZ;

  double highestX, highestY, highestZ;

  double lfEncoder, lbEncoder, rfEncoder, rbEncoder;

  

  public DifferentialDrive drive;


  public NeoDriveBase()
  {

    try{

      navx = new AHRS(Port.kMXP); 


    } catch (RuntimeException ex){

      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);

    }
    // Opens all our motor controllers when subsystem is created by making calls to CANSparkMax Library

    lf_motor = new CANSparkMax(RobotMap.LEFT_FRONT_SPARK, MotorType.kBrushless);
    lb_motor = new CANSparkMax(RobotMap.LEFT_BACK_SPARK, MotorType.kBrushless);
    rf_motor = new CANSparkMax(RobotMap.RIGHT_FRONT_SPARK, MotorType.kBrushless);
    rb_motor = new CANSparkMax(RobotMap.RIGHT_BACK_SPARK, MotorType.kBrushless);

  }

  
  
  @Override
  public void initDefaultCommand() {
    // Sets our default to be driving via user input at all times, using setDefaultCommand();
     setDefaultCommand(new DriveMotors());
  }



  public void periodic(){

    publishData();
    checkTemp();

  }

  public void checkTemp(){

    
    CANSparkMax[] motors = {lb_motor, lf_motor, rf_motor, rb_motor};


    for(CANSparkMax motor : motors){
      if(motor.getMotorTemperature() > 65){

        String err = "DRIVE MOTOR OVERHEAT,MOTOR DISABLED";
        
        SmartDashboard.putString("ERROR: ", err);
        
        motor.set(0);

      }
    }


  }

  
  public void drive(){
    
    // Takes "True" value of our joysticks (deadzone fixed)

    lf_motor.set(Robot.m_oi.TrueLeftX() * -1);
    lb_motor.set(Robot.m_oi.TrueLeftX() * - 1);
    rf_motor.set(Robot.m_oi.TrueRightX());
    rb_motor.set(Robot.m_oi.TrueRightX());
    
  
  }

  public void disable(){
  
    // Handles stopping drive when motion needs to be halted quickly

    drive.tankDrive(0, 0);

  }
  
  
  public void strafeLeft(){
    
    // Mecanum Trigger bound strafing, see command for how this is accessed

    lf_motor.set(Robot.m_oi.controller.getRawAxis(2)*-1);
    lb_motor.set(Robot.m_oi.controller.getRawAxis(2) );
    rf_motor.set(Robot.m_oi.controller.getRawAxis(2)*-1);
    rb_motor.set(Robot.m_oi.controller.getRawAxis(2) );
    

  }
  
  public void strafeRight(){

    // Enables Trigger strafing, see above
    
    lf_motor.set(Robot.m_oi.controller.getRawAxis(3));
    lb_motor.set(Robot.m_oi.controller.getRawAxis(3) *-1);
    rf_motor.set(Robot.m_oi.controller.getRawAxis(3) );
    rb_motor.set(Robot.m_oi.controller.getRawAxis(3)*-1);

  
  }

  public void publishData(){

    gyroRead = navx.getAngle();
    accelX = navx.getRawAccelX();
    accelY = navx.getRawAccelY();
    accelZ = navx.getRawAccelZ();

    SmartDashboard.putNumber("Gyro Read: ", gyroRead);
    SmartDashboard.putNumber("Accel X: ", accelX);
    SmartDashboard.putNumber("Accel Y: ", accelY);
    SmartDashboard.putNumber("Accel Z: ", accelZ);

    lfEncoder = lf_motor.getEncoder().getPosition();
    lbEncoder = lb_motor.getEncoder().getPosition();
    rfEncoder = rf_motor.getEncoder().getPosition();
    rbEncoder = rb_motor.getEncoder().getPosition();

    SmartDashboard.putNumber("LF Encoder: ", lfEncoder);
    SmartDashboard.putNumber("LB Encoder: ", lbEncoder);
    SmartDashboard.putNumber("RF Encoder: ", rfEncoder);
    SmartDashboard.putNumber("RB Encoder: ", rbEncoder);

  }



  public double convF(double tempC){

    return (tempC * (9/5)) + 32;
  }

}
